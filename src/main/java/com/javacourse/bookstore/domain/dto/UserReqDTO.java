package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class UserReqDTO {
    private Long phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String nickName;
    private Integer age;
    private LocalDate dateOfBirth;
}
