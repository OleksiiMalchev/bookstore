package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class UserReqDTO {
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate dateOfBirth;
    private long phoneNumber;
    private String email;
    private String nickName;
}
