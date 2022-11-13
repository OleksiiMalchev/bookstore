package com.javacourse.bookstore.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "author")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_Name")
    private String lastName;
    @Column(name = "sur_Name")
    private String surName;
    @Column(name = "date_Of_Birth")
    private LocalDate dateOfBirth;
    @Column(name = "date_Of_Death")
    private LocalDate dateOfDeath;
    @Column(name = "biography")
    private String biography;
    @Column(name = "country_Of_Birth")
    private String countryOfBirth;
    @Column(name = "gender")
    private String gender;

}