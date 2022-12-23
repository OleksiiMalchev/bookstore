package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Order;
import com.javacourse.bookstore.domain.dto.OrderReqDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public Order mapToOrder(OrderReqDTO orderReqDTO) {
        if (orderReqDTO != null) {
            return Order.builder()
                    .customerId(orderReqDTO.getCustomerId())
                    .createdAt(LocalDateTime.now())
                    .changeAt(LocalDateTime.now())
                    .build();
        }
        return null;
    }

    public OrderRespDTO mapToOrderRespDTO(Order order) {
        if (order != null) {
            return OrderRespDTO.builder()
                    .id(order.getId())
                    .createdAt(order.getCreatedAt())
                    .changeAt(order.getChangeAt())
//                    .orderStatus(order.getOrderStatus())
//                    .orderDetails(order.getOrderDetails())
                    .build();
        }
        return null;
    }
}
