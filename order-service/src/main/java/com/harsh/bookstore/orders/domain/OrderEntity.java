package com.harsh.bookstore.orders.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String customerPhone;

    @Column(nullable = false)
    private String deliveryAddressLine1;

    private String deliveryAddressLine2;

    @Column(nullable = false)
    private String deliveryAddressCity;

    @Column(nullable = false)
    private String deliveryAddressState;

    @Column(nullable = false)
    private String deliveryAddressZipCode;

    @Column(nullable = false)
    private String deliveryAddressCountry;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    private String comments;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItemEntity> items = new LinkedHashSet<>();

    protected OrderEntity() {}

    OrderEntity(
            String orderNumber,
            String username,
            String customerName,
            String customerEmail,
            String customerPhone,
            String deliveryAddressLine1,
            String deliveryAddressLine2,
            String deliveryAddressCity,
            String deliveryAddressState,
            String deliveryAddressZipCode,
            String deliveryAddressCountry,
            String comments) {
        this.orderNumber = orderNumber;
        this.username = username;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.deliveryAddressLine1 = deliveryAddressLine1;
        this.deliveryAddressLine2 = deliveryAddressLine2;
        this.deliveryAddressCity = deliveryAddressCity;
        this.deliveryAddressState = deliveryAddressState;
        this.deliveryAddressZipCode = deliveryAddressZipCode;
        this.deliveryAddressCountry = deliveryAddressCountry;
        this.comments = comments;
        this.status = OrderStatus.NEW;
        this.createdAt = LocalDateTime.now();
    }

    void addItem(OrderItemEntity item) {
        items.add(item);
        item.assignTo(this);
    }

    String getOrderNumber() {
        return orderNumber;
    }

    String getUsername() {
        return username;
    }

    OrderStatus getStatus() {
        return status;
    }

    Set<OrderItemEntity> getItems() {
        return items;
    }
}
