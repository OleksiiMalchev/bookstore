package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "warehouse")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="product_id")
    private Long productId;
    @Column(name = "book_quantity")
    private Integer bookQuantity;
    @Column(name = "initial_price")
    private Long initialPrice;
    @Column(name = "reserve")
    private Integer reserve;
    @Column(name = "sale")
    private Integer sale;
    @ManyToOne
    @JoinColumn(name="product_id",insertable = false, updatable = false)
    private Product product;
}
