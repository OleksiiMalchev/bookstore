package com.javacourse.bookstore.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity(name = "author")
@Getter
@Setter
@ToString
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="first_name")
    private String firstName;
    private String lastName;
    private String surName;

}
