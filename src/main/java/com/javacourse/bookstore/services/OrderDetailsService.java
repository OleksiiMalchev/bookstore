package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.OrderDetailsReqDTO;
import com.javacourse.bookstore.domain.dto.OrderDetailsRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderDetailsService {
    List<OrderDetailsRespDTO> getAllOrderDetails();
    Optional<OrderDetailsRespDTO> getOrderDetailsById(Long idOrderDetails);
    Optional<OrderDetailsRespDTO> createOrderDetails(OrderDetailsReqDTO orderDetailsReqDTO);
    Optional<OrderDetailsRespDTO> updateOrderDetails(Long idOrderDetails, OrderDetailsReqDTO orderDetailsReqDTO);
    Optional<OrderDetailsRespDTO> deleteOrderDetails(Long idOrderDetails);
}
