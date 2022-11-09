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
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String countryOfBirth;
    private Long id;
    private List<BookRespDTO> books;
}
