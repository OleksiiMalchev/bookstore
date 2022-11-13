package com.javacourse.bookstore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String surName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String biography;
    private String countryOfBirth;


    private final List<Book> books = new ArrayList<>();

    public Book addBook(Book book) {
         this.books.add(book);
         return book;
    }
    public Book delete(Book book) {
        books.remove(book);
        return book;
    }

}