package com.javacourse.bookstore.services;

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

    Optional<OrderRespDTO> createOrder(Long idCustomer);

    Optional<OrderRespDTO> updateOrder(Long id);

    Optional<OrderRespDTO> deleteOrder(Long id);
}
