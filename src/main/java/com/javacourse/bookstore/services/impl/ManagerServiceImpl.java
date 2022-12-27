package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.dto.BuyReqDTO;
import com.javacourse.bookstore.domain.dto.BuyRespDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import com.javacourse.bookstore.repositories.CustomerRepository;
import com.javacourse.bookstore.repositories.ProductRepository;
import com.javacourse.bookstore.services.BuyService;
import com.javacourse.bookstore.services.OrderDetailsService;
import com.javacourse.bookstore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements BuyService {
    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final OrderDetailsService orderDetailsService;
    private final CustomerRepository customerRepository;




    @Transactional
    @Override
    public BuyRespDTO buyBook(BuyReqDTO buyReqDTO) throws IOException {
        Long customerId = buyReqDTO.getCustomerId();
        if (customerId != null && customerRepository.existsById(customerId)) {
            Optional<OrderRespDTO> order = orderService.createOrder(buyReqDTO.getCustomerId());
            List<Long> productId = buyReqDTO.getProductId();
            for (Long l : productId) {
                if (l!= null && productRepository.existsById(l)) {
                    OrderDetails orderDetailsToSave = OrderDetails.builder()
                            .orderId(order.get().getId()).productId(l)
                            .quantity(buyReqDTO.getQuantity().get(Math.toIntExact(productId.indexOf(l)))).build();
                    orderDetailsService.createOrderDetails(orderDetailsToSave);
                } else {
                    throw new IOException("There is no such product.");
                }
            }
            return BuyRespDTO.builder().order(orderService.getOrderById(order.get().getId()).get()).build();
        } else {
            throw new IOException("Register to buy books.Please.");
        }
    }
}
