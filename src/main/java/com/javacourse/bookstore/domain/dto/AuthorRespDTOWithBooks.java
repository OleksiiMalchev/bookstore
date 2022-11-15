package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
public class AuthorRespDTOWithBooks {
    private Long id;
    private String firstName;
    private String lastName;
    private String countryOfBirth;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private List<BookRespDTO> books;
}
