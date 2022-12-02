package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Table(name="book")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="author_id")
    private Long authorId;
    @Column(name="price")
    private Long price;
    @Column(name="cost")
    private Long cost;
    @Column(name="title")
    private String title;
    @Column(name="cover")
    private String cover;
    @Column(name="publishing_house")
    private String publishingHouse;
    @Column(name="bar_code")
    private Integer barCode;
    @Column(name="pages")
    private Integer pages;
    @Column(name="isbn")
    private Integer isbn;
    @Column(name="year_of_publication")
    private LocalDate yearOfPublication;
    @ManyToOne
    @JoinColumn(name="author_id",insertable = false, updatable = false)
    private Author author;
}
