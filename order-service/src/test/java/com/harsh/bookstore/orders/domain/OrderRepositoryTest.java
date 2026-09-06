package com.harsh.bookstore.orders.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.harsh.bookstore.orders.ContainersConfig;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest(properties = "spring.test.database.replace=none")
@Import(ContainersConfig.class)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveAndLoadOrderWithItems() {
        OrderEntity order = newOrder("order-100");
        order.addItem(new OrderItemEntity("P100", "The Hunger Games", new BigDecimal("34.00"), 2));

        orderRepository.saveAndFlush(order);

        OrderEntity saved = orderRepository
                .findByUsernameAndOrderNumber("alice", "order-100")
                .orElseThrow();
        assertThat(saved.getStatus()).isEqualTo(OrderStatus.NEW);
        assertThat(saved.getItems()).singleElement().satisfies(item -> {
            assertThat(item.getCode()).isEqualTo("P100");
            assertThat(item.getQuantity()).isEqualTo(2);
            assertThat(item.getPrice()).isEqualByComparingTo("34.00");
        });
    }

    @Test
    void shouldFindOrdersByStatus() {
        orderRepository.saveAndFlush(newOrder("order-101"));

        assertThat(orderRepository.findByStatus(OrderStatus.NEW))
                .extracting(OrderEntity::getOrderNumber)
                .containsExactly("order-101");
    }

    @Test
    void shouldNotReturnAnotherUsersOrder() {
        orderRepository.saveAndFlush(newOrder("order-102"));

        assertThat(orderRepository.findByUsernameAndOrderNumber("bob", "order-102")).isEmpty();
    }

    private OrderEntity newOrder(String orderNumber) {
        return new OrderEntity(
                orderNumber,
                "alice",
                "Alice Doe",
                "alice@example.com",
                "+1-555-0100",
                "42 Book Street",
                null,
                "Pune",
                "Maharashtra",
                "411001",
                "India",
                "Leave at the front desk");
    }
}
