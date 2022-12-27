package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.dto.BuyReqDTO;
import com.javacourse.bookstore.domain.dto.BuyRespDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import com.javacourse.bookstore.mappers.BuyReqDTOToOrderReqDTO;
import com.javacourse.bookstore.mappers.OrderDetailsMapper;
import com.javacourse.bookstore.repositories.CustomerRepository;
import com.javacourse.bookstore.repositories.OrderRepository;
import com.javacourse.bookstore.repositories.ProductRepository;
import com.javacourse.bookstore.services.BuyService;
import com.javacourse.bookstore.services.OrderDetailsService;
import com.javacourse.bookstore.services.OrderService;
import com.javacourse.bookstore.services.ProductService;
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
    private final BuyReqDTOToOrderReqDTO buyReqDTOToOrderReqDTO;
    private final ProductRepository productRepository;

    private final ProductService productService;
    private final OrderDetailsService orderDetailsService;
    private final CustomerRepository customerRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final OrderRepository orderRepository;



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


//        Optional<OrderRespDTO> order = orderService.createOrder(buyReqDTO.getCustomerId());
//        Long idOrder = order.get().getId();
//        OrderDetailsReqDTO orderDetailsReqDTO = buyReqDTOToOrderReqDTO.mapToOrderDetailsReqDTO(buyReqDTO);
//        orderDetailsReqDTO.setOrderId(idOrder);
//        order.get().setOrderStatus(OrderStatus.PROCESSED);
//        orderService.updateOrder(order.get().getId());
//        OrderDetailsRespDTO orderDetails = orderDetailsService.createOrderDetails(orderDetailsReqDTO).get();
//        Optional<Product> productById = productRepository.findById(buyReqDTO.getProductId());

//        return BuyRespDTO.builder()
//                .orderDetails(orderDetails)
//                .order(order.get())
//                .productRespDTOWithWarehouseInfo(productService.getProductByIdWithWarehouseInfo(buyReqDTO.getProductId()).get())
//                .build();

//    }
}
