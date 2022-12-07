package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "order_status")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_status")
    private String nameStatus;
    @Column(name = "order_id")
    private Long orderId;
    @ManyToOne
    @JoinColumn(name="order_id",insertable = false, updatable = false)
    private Order order;
}
