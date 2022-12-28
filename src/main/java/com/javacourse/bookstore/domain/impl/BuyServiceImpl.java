package com.javacourse.bookstore.domain.impl;

import com.javacourse.bookstore.domain.dto.BuyReqDto;
import com.javacourse.bookstore.services.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl {
    private final WarehouseService warehouseService;
//    private final OrderStatusReq


    public BuyReqDto buy(BuyReqDto buyReqDto){  //idCustomer, idProd, quantity
        return buyReqDto;
    }
}
