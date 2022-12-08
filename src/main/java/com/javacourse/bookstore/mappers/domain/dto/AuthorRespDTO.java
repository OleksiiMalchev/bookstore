package com.javacourse.bookstore.mappers.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorRespDTO {
    private Long authorId;
    private String firstName;
    private String lastName;
}
