package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.OrderReqDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface OrderService {
    List<OrderRespDTO> getAllOrder();

    List<OrderRespDTO> getAllOrderByCustomerId(Long idCustomer);

    List<OrderRespDTO> getAllOrderByStatus(String statusName);

    Optional<OrderRespDTO> getOrderById(Long id);

    Optional<OrderRespDTO> getOrderByCustomerIdAndStatus(Long idCustomer, String statusName);

    Optional<OrderRespDTO> createOrder(OrderReqDTO orderReqDTO);

    Optional<OrderRespDTO> updateOrder(Long id, OrderReqDTO orderReqDTO);

    Optional<OrderRespDTO> deleteOrder(Long id);
}
