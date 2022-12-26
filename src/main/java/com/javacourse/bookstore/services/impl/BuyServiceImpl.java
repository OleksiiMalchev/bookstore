package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.Product;
import com.javacourse.bookstore.domain.Warehouse;
import com.javacourse.bookstore.domain.dto.*;
import com.javacourse.bookstore.mappers.BuyReqDTOToOrderReqDTO;
import com.javacourse.bookstore.mappers.OrderDetailsMapper;
import com.javacourse.bookstore.repositories.ProductRepository;
import com.javacourse.bookstore.services.BuyService;
import com.javacourse.bookstore.services.OrderDetailsService;
import com.javacourse.bookstore.services.OrderService;
import com.javacourse.bookstore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService {
    private final OrderService orderService;
    private final BuyReqDTOToOrderReqDTO buyReqDTOToOrderReqDTO;
    private final ProductRepository productRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final ProductService productService;
    private final OrderDetailsService orderDetailsService;

    @Transactional
    @Override
    public BuyRespDTO buyBook(BuyReqDTO buyReqDTO) {
        Optional<OrderRespDTO> order = orderService.createOrder(buyReqDTOToOrderReqDTO.mapToOrderReqDTO(buyReqDTO));
        Long idOrder = order.get().getId();
        OrderDetailsReqDTO orderDetailsReqDTO = buyReqDTOToOrderReqDTO.mapToOrderDetailsReqDTO(buyReqDTO);
        orderDetailsReqDTO.setOrderId(idOrder);
        OrderDetailsRespDTO orderDetails = orderDetailsService.createOrderDetails(orderDetailsReqDTO).get();
        Optional<Product> productById = productRepository.findById(buyReqDTO.getProductId());
        Warehouse warehouse = productById.get()
                .getWarehouses()
                .stream()
                .filter(w -> w.getProductId().equals(productById.get().getId())).findFirst().get();
        if (warehouse.getBookQuantity() >= buyReqDTO.getQuantity()) {
            Integer bookQuantity = warehouse.getBookQuantity();
            Integer quantityInReq = buyReqDTO.getQuantity();
            warehouse.setBookQuantity(bookQuantity - quantityInReq);
            warehouse.setReserve(quantityInReq);
        }
        return BuyRespDTO.builder()
                .orderDetails(orderDetails)
                .order(order.get())
                .productRespDTOWithWarehouseInfo(productService.getProductByIdWithWarehouseInfo(buyReqDTO.getProductId()).get())
                .build();
    }
}
