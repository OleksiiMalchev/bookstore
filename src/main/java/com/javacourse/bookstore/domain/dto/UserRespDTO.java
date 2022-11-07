package com.javacourse.bookstore.domain.dto;

public class UserRespDTO {
    private String firstName;
    private String email;
    private String nickName;
    private Long IDUser;

    public UserRespDTO(String firstName,
                       String email,
                       String nickName,
                       Long IDUser) {
        this.firstName = firstName;
        this.email = email;
        this.nickName = nickName;
        this.IDUser = IDUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}