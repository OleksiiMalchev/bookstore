package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.BuyReqDTO;
import com.javacourse.bookstore.domain.dto.BuyRespDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface BuyService {
    BuyRespDTO buyBook(BuyReqDTO buyReqDTO) throws InterruptedException, IOException;
}
