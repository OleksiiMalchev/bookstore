package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.dto.BuyReqDTO;
import com.javacourse.bookstore.domain.dto.OrderDetailsReqDTO;
import com.javacourse.bookstore.domain.dto.OrderReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyReqDTOToOrderReqDTO {
    public OrderReqDTO mapToOrderReqDTO(BuyReqDTO buyReqDTO) {
        if (buyReqDTO != null) {
            return OrderReqDTO.builder()
                    .customerId(buyReqDTO.getCustomerId())
                    .build();
        }
        return null;
    }

    public OrderDetailsReqDTO mapToOrderDetailsReqDTO(BuyReqDTO buyReqDTO) {
        if (buyReqDTO != null) {
            return OrderDetailsReqDTO.builder()
                    .productId(buyReqDTO.getProductId())
                    .quantity(buyReqDTO.getQuantity())
                    .build();
        }
        return null;
    }
}
