package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Order;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTOWithStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    @Autowired
    private final OrderDetailsMapper orderDetailsMapper;


//    public Order mapToOrder(OrderReqDTO orderReqDTO) {
//        if (orderReqDTO != null) {
//            return Order.builder()
//                    .customerId(orderReqDTO.getCustomerId())
//                    .createdAt(LocalDateTime.now())
//                    .changeAt(LocalDateTime.now())
//                    .build();
//        }
//        return null;
//    }

    public OrderRespDTO mapToOrderRespDTO(Order order) {
        if (order != null) {
            return OrderRespDTO.builder()
                    .id(order.getId())
                    .createdAt(order.getCreatedAt())
                    .changeAt(order.getChangeAt())
                    .orderStatus(order.getOrderStatus())
                    .build();
        }
        return null;
    }
    public OrderRespDTOWithStatus mapToOrderRespDTONew(Order order) {
        if (order != null) {
            return OrderRespDTOWithStatus.builder()
                    .id(order.getId())
                    .createdAt(order.getCreatedAt())
                    .changeAt(order.getChangeAt())
                    .orderStatus(order.getOrderStatus())
                    .orderDetails(order.getOrderDetails()
                            .stream()
                            .map(orderDetailsMapper::mapToOrderDetailsRespDTO).toList())
                    .build();
        }
        return null;
    }
}


