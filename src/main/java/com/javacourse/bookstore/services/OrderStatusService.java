package com.javacourse.bookstore.services;


import com.javacourse.bookstore.domain.dto.OrderStatusReqDTO;
import com.javacourse.bookstore.domain.dto.OrderStatusRespDTO;

import java.util.List;
import java.util.Optional;

public interface OrderStatusService {
    List<OrderStatusRespDTO> getAllOrderStatus();

    Optional<OrderStatusRespDTO> getOrderStatusById(Long idOrderStatus);

    Optional<OrderStatusRespDTO> createOrderStatus(OrderStatusReqDTO orderStatusReqDTO);

    Optional<OrderStatusRespDTO> updateOrderStatus(Long idOrderStatus, OrderStatusReqDTO orderStatusReqDTO );

    Optional<OrderStatusRespDTO> deleteCustomer(Long idOrderStatus);
}
