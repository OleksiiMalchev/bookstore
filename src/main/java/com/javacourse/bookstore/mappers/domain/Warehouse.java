package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Book> books;
}
