package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.dto.OrderDetailsReqDTO;
import com.javacourse.bookstore.domain.dto.OrderDetailsRespDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsMapper {
    public OrderDetails mapToOrderDetails(OrderDetailsReqDTO orderDetailsReqDTOReqDTO) {
        if (orderDetailsReqDTOReqDTO != null) {
            return OrderDetails.builder()
                    .orderId(orderDetailsReqDTOReqDTO.getOrderId())
                    .productId(orderDetailsReqDTOReqDTO.getProductId())
                    .quantity(orderDetailsReqDTOReqDTO.getQuantity())
                    .build();
        }
        return null;
    }

    public  OrderDetailsRespDTO mapToOrderDetailsRespDTO(OrderDetails orderDetails) {
        if (orderDetails != null) {
            return OrderDetailsRespDTO.builder()
                    .orderId(orderDetails.getOrderId())
                    .productId(orderDetails.getProductId())
                    .quantity(orderDetails.getQuantity())
                    .build();
        }
        return null;
    }
}
