package com.javacourse.bookstore.domain;


import java.util.ArrayList;
import java.util.List;

public class Author {
    private final List<Book> books = new ArrayList<>();
    private Long ID;
    private String firstName;
    private String lastName;
    private String surName;
    private long dateOfBirth;
    private long dateOfDeath;
    private String biography;
    private String countryOfBirth;


    public Author(String firstName,
                  String lastName,
                  long dateOfBirth
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(long dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public Book delete(Book book) {
        books.remove(book);
        return book;
    }

}