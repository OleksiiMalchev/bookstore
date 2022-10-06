package com.javacourse.bookstore.domain.dto;

import java.util.List;

public class AuthorRespDTOWithBooks {
    private String firstName;
    private String lastName;
    private long dateOfBirth;
    private long dateOfDeath;
    private String countryOfBirth;
    private Long ID;
    List<BookRespDTO> books;

    public AuthorRespDTOWithBooks(String firstName,
                                  String lastName,
                                  long dateOfBirth,
                                  Long ID,
                                  List<BookRespDTO> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.ID = ID;
        this.books = books;
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

    public List<BookRespDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookRespDTO> books) {
        this.books = books;
    }
}
