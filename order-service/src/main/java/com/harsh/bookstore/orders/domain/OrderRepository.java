package com.harsh.bookstore.orders.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByOrderNumber(String orderNumber);

    List<OrderEntity> findByStatus(OrderStatus status);

    @EntityGraph(attributePaths = "items")
    Optional<OrderEntity> findByUsernameAndOrderNumber(String username, String orderNumber);
}
