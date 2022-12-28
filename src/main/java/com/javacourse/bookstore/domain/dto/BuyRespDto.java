package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BuyRespDTO {
    private OrderRespDTO order;
    private OrderDetailsRespDTO orderDetails;
    private ProductRespDTOWithWarehouseInfo productRespDTOWithWarehouseInfo;

}
