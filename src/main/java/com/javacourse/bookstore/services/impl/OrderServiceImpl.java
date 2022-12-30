package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.Order;
import com.javacourse.bookstore.domain.OrderDetails;
import com.javacourse.bookstore.domain.OrderStatus;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTOWithStatus;
import com.javacourse.bookstore.mappers.OrderMapper;
import com.javacourse.bookstore.repositories.CustomerRepository;
import com.javacourse.bookstore.repositories.OrderRepository;
import com.javacourse.bookstore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;

    @Override
    public List<OrderRespDTO> getAllOrder() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::mapToOrderRespDTO)
                .toList();
    }

    @Override
    public List<OrderRespDTO> getAllOrderByCustomerId(Long idCustomer) {
        return orderRepository.getAllOrderByCustomerId(idCustomer)
                .stream()
                .map(orderMapper::mapToOrderRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderRespDTO> getAllOrderByStatus(String statusName) {
        return orderRepository.getAllOrderByStatus(statusName)
                .stream()
                .map(orderMapper::mapToOrderRespDTO)
                .toList();
    }


    @Override
    public Optional<OrderRespDTOWithStatus> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::mapToOrderRespDTONew);

    }

    @Override
    public Optional<OrderRespDTO> getOrderByCustomerIdAndStatus(Long idCustomer, String statusName) {
        return orderRepository.getOrderByCustomerIdAndStatus(idCustomer, statusName)
                .map(orderMapper::mapToOrderRespDTO);
    }


    @Override
    public OrderRespDTO createOrder(Long customerId) {
        if (customerId != null && customerRepository.existsById(customerId)) {
            Order order = Order.builder().customerId(customerId).createdAt(LocalDateTime.now())
                    .changeAt(LocalDateTime.now()).orderStatus(OrderStatus.NEW).build();
            Order saveOrder = orderRepository.save(order);
            return orderMapper.mapToOrderRespDTO(saveOrder);
        }
        return null;
    }

    @Transactional
    @Override
    public Optional<OrderRespDTO> updateOrder(Order order, List<OrderDetails> orderDetailsList, OrderStatus orderStatus) {
        order.setOrderDetails(orderDetailsList);
        order.setChangeAt(LocalDateTime.now());
        order.setOrderStatus(orderStatus);
        Order saveOrder = orderRepository.save(order);
        return Optional.ofNullable(orderMapper.mapToOrderRespDTO(saveOrder));
    }

    @Override
    public Optional<OrderRespDTO> deleteOrder(Long id) {
        Optional<OrderRespDTO> orderRespDTO = orderRepository.findById(id)
                .map(orderMapper::mapToOrderRespDTO);
        if (orderRespDTO.isPresent()) {
            orderRepository.deleteById(id);
        }
        return orderRespDTO;
    }
}
