package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class AuthorRespDTOWithBooks {
    private Long id;
    private String firstName;
    private String lastName;
    private List<BookRespDTOForAuthorWithBooks> books;
}
