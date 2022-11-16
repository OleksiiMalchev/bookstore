package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class BookReqDTO {
    private Long id;
    private Long authorId;
    private Long cost;
    private String title;
    private String cover;
    private String publishingHouse;
    private Integer barCode;
    private Integer pages;
    private Integer isbn;
    private LocalDate yearOfPublication;
}
