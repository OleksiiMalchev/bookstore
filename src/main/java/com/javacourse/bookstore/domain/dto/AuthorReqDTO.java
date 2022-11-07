package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter

@Builder //TODO
public class AuthorReqDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String surName;
    private LocalDate dateOfDeath;
    private String biography;
    private String countryOfBirth;
    private Long id;
}
