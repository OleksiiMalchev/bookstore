package com.javacourse.bookstore.domain.dto;

import com.javacourse.bookstore.domain.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BookReqDTO {
    private String title;
    private Author author;
    private String cover;
    private String publishingHouse;
    private int yearOfPublication;
    private long cost;
    private int barCode;
    private Long ID;
    private int pages;
    private Long authorID;
}
