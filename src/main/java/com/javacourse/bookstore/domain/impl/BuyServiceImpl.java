package com.javacourse.bookstore.domain.impl;

import com.javacourse.bookstore.domain.Order;
import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.dto.BuyReqDto;
import com.javacourse.bookstore.domain.dto.ProductRespDTOWithWarehouseInfo;
import com.javacourse.bookstore.services.OrderStatusService;
import com.javacourse.bookstore.services.ProductService;
import com.javacourse.bookstore.services.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl {
//    private final WarehouseService warehouseService;
//    private final ProductService productService;
//    private final OrderStatusService orderStatusService;
//    private final OrderService orderService;
//    private final OrderDetailsService orderDetailsService;
//
//
//    public BuyReqDto buy(BuyReqDto buyReqDto) {  //idCustomer, idProd, quantity
//        ProductRespDTOWithWarehouseInfo productByIdWithWarehouseInfo = productService.getProductByIdWithWarehouseInfo(buyReqDto.getIdProduct()).get();
//        Integer warehouseQuantity = productByIdWithWarehouseInfo.getWarehouse().getBookQuantity();
//        if (buyReqDto.getBookQuantity() >= warehouseQuantity) {
//            OrderRequestDto orderRequestDto = OrderRequestDto.builder().idCustomer(idCustomer).createAt().build();
//            OrderRespDto newOrder = orderService.create(OrderRequestDto).get();
//            orderStatusService.createOrderStatus("NEW");
//            OrderDetailsReqDto orderDetailsReqDto = OrderDetailsReqDto.builder()
//                    .orderId(newOrder.getId)
//                    .productId(idProduct)
//                    .quantity(quantity)
//                    .build;
//            OrderDetailsRespDto newOrderDetails = orderDetailsService.create(orderDetailsReqDto).get();
//
//        warehouseService.updateWarehouse(productByIdWithWarehouseInfo.)
//        } else {
//            return null;
//        }
//    }
}
