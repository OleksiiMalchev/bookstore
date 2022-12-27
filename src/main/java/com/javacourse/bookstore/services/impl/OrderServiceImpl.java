package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.Order;
import com.javacourse.bookstore.domain.OrderStatus;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTOWithStatus;
import com.javacourse.bookstore.mappers.OrderMapper;
import com.javacourse.bookstore.repositories.OrderRepository;
import com.javacourse.bookstore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Optional<OrderRespDTOWithStatus> getOrderById(Long id) {
        Optional<OrderRespDTOWithStatus> orderRespDTOWithStatus = orderRepository.findById(id)
                .map(orderMapper::mapToOrderRespDTONew);
        return orderRespDTOWithStatus;

    }

    @Override
    public Optional<OrderRespDTO> getOrderByCustomerIdAndStatus(Long idCustomer, String statusName) {
        return orderRepository.getOrderByCustomerIdAndStatus(idCustomer, statusName)
                .map(orderMapper::mapToOrderRespDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Optional<OrderRespDTO> createOrder(Long customerId) {
        if (customerId != null && orderRepository.existsById(customerId)) {
            Order order = Order.builder().customerId(customerId).createdAt(LocalDateTime.now())
                    .changeAt(LocalDateTime.now()).orderStatus(OrderStatus.NEW).build();
            Order saveOrder = orderRepository.save(order);
            return orderRepository.findById(saveOrder.getId())
                    .map(orderMapper::mapToOrderRespDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrderRespDTO> updateOrder(Long id) {
//        if(!orderRepository.findById(id).get().getOrderStatus().equals("NEW")){
//            Order order = orderRepository.findById(id).get();
//            order.setCreatedAt(LocalDateTime.now());
//            return Optional.ofNullable(orderMapper.mapToOrderRespDTO(order));
//        } else {
//
//        }
        return Optional.empty();
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
