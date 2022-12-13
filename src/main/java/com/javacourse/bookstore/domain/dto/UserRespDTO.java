package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserRespDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String nickName;
}
