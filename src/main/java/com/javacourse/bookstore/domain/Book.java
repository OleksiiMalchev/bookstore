package com.javacourse.bookstore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class Book {

    private String title;
    private Author author;
    private String cover;
    private String publishingHouse;
    private LocalDate yearOfPublication;
    private long price;
    private long cost;
    private int barCode;
    private Long id;
    private int pages;
    private long ESBI;
    private Long authorID;

}
