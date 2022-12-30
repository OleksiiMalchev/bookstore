package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Order;
import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.OrderStatus;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTOWithStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface OrderService {
    List<OrderRespDTO> getAllOrder();

    List<OrderRespDTO> getAllOrderByCustomerId(Long idCustomer);

    List<OrderRespDTO> getAllOrderByStatus(String statusName);

    Optional<OrderRespDTOWithStatus> getOrderById(Long id);

    Optional<OrderRespDTO> getOrderByCustomerIdAndStatus(Long idCustomer, String statusName);

    OrderRespDTO createOrder(Long idCustomer);

    Optional<OrderRespDTO> updateOrder(Order order, List<OrderDetails> orderDetailsList, OrderStatus orderStatus);

    Optional<OrderRespDTO> deleteOrder(Long id);
}
