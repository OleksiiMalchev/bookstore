package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class WarehouseRespDTO {
    private Long id;
    private Long productId;
    private Integer bookQuantity;
    private Integer reserve;
    private Integer sale;
}
