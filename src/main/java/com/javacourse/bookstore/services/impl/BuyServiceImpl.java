package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.dto.BuyReqDTO;
import com.javacourse.bookstore.domain.dto.BuyRespDTO;
import com.javacourse.bookstore.services.BuyService;
import com.javacourse.bookstore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService {
    private final OrderService orderService;
    @Override
    public Optional<BuyRespDTO> buyBook(BuyReqDTO buyReqDTO) {
    //    orderService.createOrder(buyReqDTO)
        return Optional.empty();
    }
}
