package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorRespDTO {
    private String firstName;
    private String lastName;
    private long dateOfBirth;
    private long dateOfDeath;
    private String countryOfBirth;
    private Long ID;

}
