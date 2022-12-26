package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BuyRespDTO {
    private Long productId;
    private Integer quantity;
    private String address;
    private String firstName;
    private String lastName;
}
