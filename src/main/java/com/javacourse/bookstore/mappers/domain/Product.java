package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name="product")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="book_id")
    private Long bookId;
    @Column(name="price")
    private Long price;
    @Lob
    @Column(name="description", columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Warehouse> warehouses;
    @ManyToOne
    @JoinColumn(name="book_id",insertable = false, updatable = false)
    private Book book;
}
