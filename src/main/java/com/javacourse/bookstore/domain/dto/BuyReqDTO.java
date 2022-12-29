package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class BuyReqDTO {
    private Long customerId;
    private String address;
    private List<ProductDTO> products;

    @Setter
    @Getter
    @Builder
    public static class ProductDTO {
        private Integer quantity;
        private Long productId;
    }
}
