package com.javacourse.bookstore.domain.dto;

public class BookRespDTO {
    private String title;
    private String cover;
    private String publishingHouse;
    private int yearOfPublication;
    private long price;
    private int barCode;
    private Long ID;
    private int pages;
    private String ESBI;
    private AuthorRespDTOID author;


    public BookRespDTO(String title,
                       AuthorRespDTOID author,
                       String cover,
                       String publishingHouse,
                       int yearOfPublication,
                       long price,
                       int barCode,
                       Long ID,
                       int pages,
                       String ESBI) {
        this.title = title;
        this.author = author;
        this.cover = cover;
        this.publishingHouse = publishingHouse;
        this.yearOfPublication = yearOfPublication;
        this.price = price;
        this.barCode = barCode;
        this.ID = ID;
        this.pages = pages;
        this.ESBI = ESBI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public long getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = price;
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

    public String getESBI() {
        return ESBI;
    }

    public void setESBI(String ESBI) {
        this.ESBI = ESBI;
    }

    public AuthorRespDTOID getAuthorRespDTO() {
        return author;
    }

    public void setAuthorRespDTO(AuthorRespDTOID author) {
        this.author = author;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}