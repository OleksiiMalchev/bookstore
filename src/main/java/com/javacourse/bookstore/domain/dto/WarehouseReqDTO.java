package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class WarehouseReqDTO {
    private Long productId;
    private Integer bookQuantity;
}
