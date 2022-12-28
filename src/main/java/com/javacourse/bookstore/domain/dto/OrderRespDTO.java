package com.javacourse.bookstore.domain.dto;

import com.javacourse.bookstore.domain.OrderDetails;
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
    private List<OrderStatus> orderStatus;
    private List<OrderDetails> orderDetails;
}

