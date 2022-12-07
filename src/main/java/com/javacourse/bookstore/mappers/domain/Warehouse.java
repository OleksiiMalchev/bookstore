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
    @Column(name = "book_quantity")
    private Integer bookQuantity;
    @Column(name = "initional_price")
    private Long initionalPrice;
    @Column(name = "reserve")
    private Integer reserve;
    @Column(name = "sale")
    private Integer sale;
    @ManyToOne
    @JoinColumn(name="book_id",insertable = false, updatable = false)
    private Book book;
}
