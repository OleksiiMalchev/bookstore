package com.javacourse.bookstore.mappers.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductRespDTOWithWarehouseInfo {
    private Long id;
    private Long price;
    private String description;
    private BookRespDTO book;
    private WarehouseRespDTO warehouse;

}
