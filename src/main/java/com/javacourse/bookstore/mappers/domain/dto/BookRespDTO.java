package com.javacourse.bookstore.mappers.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class BookRespDTO {
    private Long id;
    private Long price;
    private String title;
    private String cover;
    private String publishingHouse;
    private Integer barCode;
    private Integer pages;
    private Integer isbn;
    private LocalDate yearOfPublication;
    private AuthorRespDTOID author;
}
