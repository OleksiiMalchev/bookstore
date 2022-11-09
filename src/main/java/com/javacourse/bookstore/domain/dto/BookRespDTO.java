package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class BookRespDTO {
    private String title;
    private String cover;
    private String publishingHouse;
    private LocalDate yearOfPublication;
    private long price;
    private int barCode;
    private Long id;
    private int pages;
    private long ESBI;
    private AuthorRespDTOID author;

//
//    public void setPrice() {
//        this.price = price;
//    }
//
//    public AuthorRespDTOID getAuthorRespDTO() {
//        return author;
//    }
//
//    public void setAuthorRespDTO(AuthorRespDTOID author) {
//        this.author = author;
//    }

}
