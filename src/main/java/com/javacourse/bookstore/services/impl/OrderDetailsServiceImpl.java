package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.dto.OrderDetailsReqDTO;
import com.javacourse.bookstore.domain.dto.OrderDetailsRespDTO;
import com.javacourse.bookstore.mappers.OrderDetailsMapper;
import com.javacourse.bookstore.repositories.OrderDetailsRepository;
import com.javacourse.bookstore.repositories.OrderRepository;
import com.javacourse.bookstore.repositories.ProductRepository;
import com.javacourse.bookstore.services.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderDetailsRespDTO> getAllOrderDetails() {
        return orderDetailsRepository.findAll()
                .stream()
                .map(orderDetailsMapper::mapToOrderDetailsRespDTO)
                .toList();
    }

    @Override
    public Optional<OrderDetailsRespDTO> getOrderDetailsById(Long idOrderDetails) {
        return orderDetailsRepository.findById(idOrderDetails)
                .map(orderDetailsMapper::mapToOrderDetailsRespDTO);
    }

    @Transactional
    @Override
    public Optional<OrderDetailsRespDTO> createOrderDetails(OrderDetails orderDetails) {
//        Long orderId = orderDetailsReqDTO.getOrderId();
//        Long productId = orderDetailsReqDTO.getProductId();
//        if (orderId != null && orderRepository.existsById(orderId) && productId != null && productRepository.existsById(productId)) {
//            OrderDetails orderDetails = orderDetailsMapper.mapToOrderDetails(orderDetailsReqDTO);
            OrderDetails save = orderDetailsRepository.save(orderDetails);
            return orderDetailsRepository
                    .findById(save.getId())
                    .map(orderDetailsMapper::mapToOrderDetailsRespDTO);
//        }
//        return Optional.empty();
    }


    @Override
    public Optional<OrderDetailsRespDTO> updateOrderDetails(Long idOrderDetails, OrderDetailsReqDTO orderDetailsReqDTO) {
        return orderDetailsRepository.findById(idOrderDetails)
                .map(oD -> {
//                    oD.setQuantity(orderDetailsReqDTO.getQuantity());
                    return oD;
                })
                .map(orderDetailsMapper::mapToOrderDetailsRespDTO);
    }


    @Override
    public Optional<OrderDetailsRespDTO> deleteOrderDetails(Long idOrderDetails) {
        Optional<OrderDetailsRespDTO> orderDetailsRespDTO = orderDetailsRepository
                .findById(idOrderDetails)
                .map(orderDetailsMapper::mapToOrderDetailsRespDTO);
        if (orderDetailsRespDTO.isPresent()) {
            orderDetailsRepository.deleteById(idOrderDetails);
        }
        return orderDetailsRespDTO;
    }
}
