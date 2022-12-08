package com.javacourse.bookstore.mappers.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class WarehouseRespDTO {
    private Long productId;
    private Integer bookQuantity;
}
