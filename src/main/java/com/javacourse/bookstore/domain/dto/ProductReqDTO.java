package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductReqDTO {
    private Long initialPrice;
    private Long bookId;
    private String description;
}
