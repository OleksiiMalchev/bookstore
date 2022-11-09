package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserRespDTO {
    private String firstName;
    private String email;
    private String nickName;
    private Long id;
}
