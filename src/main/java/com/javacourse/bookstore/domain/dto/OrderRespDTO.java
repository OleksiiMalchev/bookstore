package com.javacourse.bookstore.domain.dto;

import com.javacourse.bookstore.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class OrderRespDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime changeAt;
    private OrderStatus orderStatus;
    private List<OrderDetailsRespDTO> orderDetails;
}

