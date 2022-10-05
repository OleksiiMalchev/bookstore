package com.javacourse.bookstore.domain.dto;

import com.javacourse.bookstore.domain.Author;

public class BookReqDTO {
    private String title;
    private Author author;
    private String cover;
    private String publishingHouse;
    private int yearOfPublication;
    private long cost;
    private int barCode;
    private Long ID;
    private int pages;
    private Long authorID;

    public BookReqDTO(String title,
                      Long authorID,
                      String cover,
                      String publishingHouse,
                      int yearOfPublication,
                      long cost,
                      int barCode,
                      int pages) {
        this.title = title;
        this.authorID = authorID;
        this.cover = cover;
        this.publishingHouse = publishingHouse;
        this.yearOfPublication = yearOfPublication;
        this.cost = cost;
        this.barCode = barCode;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }
}
