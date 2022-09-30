package com.javacourse.bookstore.domain.dto;
public class BookReqDTO {
    private String title;
    private String author;
    private String cover;
    private String publishingHouse;
    private int yearOfPublication;
    private long cost;
    private int barCode;
    private Long id;
    private int pages;

    public BookReqDTO(String title, String author, String cover, String publishingHouse, int yearOfPublication, long cost, int barCode, Long id, int pages) {
        this.title = title;
        this.author = author;
        this.cover = cover;
        this.publishingHouse = publishingHouse;
        this.yearOfPublication = yearOfPublication;
        this.cost = cost;
        this.barCode = barCode;
        this.id = id;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
