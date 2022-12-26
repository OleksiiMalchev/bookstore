package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.BuyReqDTO;
import com.javacourse.bookstore.domain.dto.BuyRespDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BuyService {
    Optional<BuyRespDTO> buyBook(BuyReqDTO buyReqDTO);
}
