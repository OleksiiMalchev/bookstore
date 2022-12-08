package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "order_details")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name="order_id",insertable = false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name="product_id",insertable = false, updatable = false)
    private Product product;
}

