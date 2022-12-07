package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;

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
    @Column(name="price")
    private Long price;
    @Lob
    @Column(name="discription")
    private String discription;
    @ManyToOne
    @JoinColumn(name="book_id",insertable = false, updatable = false)
    private Book book;
}
