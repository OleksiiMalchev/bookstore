package com.javacourse.bookstore.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class OrderDetailsReqDTO {
    private List<Integer> quantity;
    private List<Long> orderId;
    private List<Long> productId;
}
