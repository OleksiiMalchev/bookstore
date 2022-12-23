package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.dto.OrderDetailsReqDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import com.javacourse.bookstore.services.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Override
    public Optional<OrderRespDTO> createOrderDetails(OrderDetailsReqDTO orderDetailsReqDTO) {
        return Optional.empty();
    }
}
