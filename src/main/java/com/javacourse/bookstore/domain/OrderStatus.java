package com.javacourse.bookstore.domain;

import com.javacourse.bookstore.domain.enam.StatusName;
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
    @Enumerated(EnumType.STRING)
    private StatusName nameStatus;
    @Column(name = "order_id")
    private Long orderId;
    @ManyToOne
    @JoinColumn(name="order_id",insertable = false, updatable = false)
    private Order order;
}
