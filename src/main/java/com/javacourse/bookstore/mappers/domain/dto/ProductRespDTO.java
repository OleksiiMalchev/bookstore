package com.javacourse.bookstore.mappers.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductRespDTO {
    private Long id;
    private Long price;
    private String description;
    private BookRespDTO book;
}
