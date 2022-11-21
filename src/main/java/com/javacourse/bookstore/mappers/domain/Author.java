package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Table(name="author")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "sur_name")
    private String surName;
    @Column(name = "biography")
    private String biography;
    @Column(name = "country_of_birth")
    private String countryOfBirth;
    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "date_of_death")
    private LocalDate dateOfDeath;
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Book> books;
}