package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.OrderStatus;
import com.javacourse.bookstore.domain.dto.OrderStatusReqDTO;
import com.javacourse.bookstore.domain.dto.OrderStatusRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MapperForOrderStatus {

    public OrderStatus orderStatusReqDTOToOrderStatus(OrderStatusReqDTO orderStatusReqDTO) {
        return Optional.ofNullable(orderStatusReqDTO)
                .map(o->OrderStatus.builder()
                        .orderId(orderStatusReqDTO.getOrderId())
                        .nameStatus(orderStatusReqDTO.getNameStatus())
                        .build())
                .orElse(null);
    }
    public Optional<OrderStatusRespDTO> orderStatusToOrderStatusRespDTO(OrderStatus orderStatus){
        return Optional.ofNullable(orderStatus)
                .map(o->OrderStatusRespDTO.builder()
                        .id(orderStatus.getId())
                        .nameStatus(orderStatus.getNameStatus())
                        .build());
    }
}

