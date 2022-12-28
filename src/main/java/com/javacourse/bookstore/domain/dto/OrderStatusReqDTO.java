package com.javacourse.bookstore.domain.dto;

import com.javacourse.bookstore.domain.enam.StatusName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderStatusReqDTO {
    private Long id;
    private StatusName nameStatus;
    private Long orderId;
}
