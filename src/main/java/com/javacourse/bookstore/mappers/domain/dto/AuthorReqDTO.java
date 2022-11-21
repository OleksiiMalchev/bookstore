package com.javacourse.bookstore.mappers.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Builder
public class AuthorReqDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String surName;
    private String biography;
    private String countryOfBirth;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
}
