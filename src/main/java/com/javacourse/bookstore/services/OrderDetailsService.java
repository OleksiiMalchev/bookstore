package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.OrderDetailsReqDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderDetailsService {
    Optional<OrderRespDTO> createOrderDetails(OrderDetailsReqDTO orderDetailsReqDTO);
}
