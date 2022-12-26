package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.BuyReqDTO;
import com.javacourse.bookstore.domain.dto.BuyRespDTO;
import org.springframework.stereotype.Service;

@Service
public interface BuyService {
    BuyRespDTO buyBook(BuyReqDTO buyReqDTO);
}
