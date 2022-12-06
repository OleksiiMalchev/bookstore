package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "order")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "shipment_id")
    private Long shipmentId;
    @Column(name = "order_status_id")
    private Long orderStatusId;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "change_at")
    private LocalDate changeAt;
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;
    //    @ManyToOne
//    @JoinColumn(name="shipment_id",insertable = false, updatable = false)
//    private Shipment shipment;
    @ManyToOne
    @JoinColumn(name = "order_status_id", insertable = false, updatable = false)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;
}
