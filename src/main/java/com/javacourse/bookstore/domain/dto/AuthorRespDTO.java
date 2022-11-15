package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AuthorRespDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String countryOfBirth;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
}
