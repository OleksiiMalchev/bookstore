package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.Product;
import com.javacourse.bookstore.domain.dto.OrderDetailsRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDetailsMapper {
    private final MapperForProduct mapperForProduct;
//    public OrderDetails mapToOrderDetails(OrderDetailsReqDTO orderDetailsReqDTO) {
//        if (orderDetailsReqDTO != null) {
//            return OrderDetails.builder()
//                    .orderId(orderDetailsReqDTO.getOrderId())
//                    .productId(orderDetailsReqDTO.getProductId())
//                    .quantity(orderDetailsReqDTO.getQuantity())
//                    .build();
//        }
//        return null;
//    }

    public  OrderDetailsRespDTO mapToOrderDetailsRespDTO(OrderDetails orderDetails) {
        if (orderDetails != null) {
            Product product = orderDetails.getProduct();
            return OrderDetailsRespDTO.builder()
                    .quantity(orderDetails.getQuantity())
                    .product(mapperForProduct.productToProductRespDTO(product))
                    .build();
        }
        return null;
    }
}
