package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.OrderReqDTO;
import com.javacourse.bookstore.domain.dto.OrderRespDTO;
import com.javacourse.bookstore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<? super List<OrderRespDTO>> getAllOrder() {
        return checkingOrders(orderService.getAllOrder());
    }

    @PostMapping("/orders")
    public ResponseEntity<? super OrderRespDTO> create(@RequestBody(required = false) OrderReqDTO orderReqDTO) {
        Optional<OrderRespDTO> orderRespDTO = orderService.createOrder(orderReqDTO);
        if (orderRespDTO.isPresent()) {
            return ResponseEntity.status(201).body(orderRespDTO);
        }
        return new ResponseEntity<>("Order not found. No action taken.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ordersCustomer/{idCustomer}")
    public ResponseEntity<? super List<OrderRespDTO>> getAllOrderByCustomerId(@PathVariable("idCustomer") Long idCustomer) {
        return checkingOrders(orderService.getAllOrderByCustomerId(idCustomer));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<? super OrderRespDTO> getOrderById(@PathVariable("id") Long id) {
        return checking(orderService.getOrderById(id));
    }


    @GetMapping("/orders/status/{status}")
    public ResponseEntity<? super List<OrderRespDTO>> getAllOrderByStatus(@PathVariable("status") String statusName) {
        return checkingOrders(orderService.getAllOrderByStatus(statusName));

    }
    @GetMapping("/ordersStatusCustomer/{idCustomer}/{status}")
    public ResponseEntity<? super OrderRespDTO> getOrderByCustomerIdAndStatus(@PathVariable("idCustomer") Long idCustomer,
                                                                              @PathVariable("status") String statusName) {
        return checking(orderService.getOrderByCustomerIdAndStatus(idCustomer,statusName));
    }
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<? super OrderRespDTO> delete(@PathVariable("id") Long id) {
        return checking(orderService.deleteOrder(id));

    }

    private static ResponseEntity<? super List<OrderRespDTO>> checkingOrders(List<OrderRespDTO> listOrders) {
        if (listOrders.isEmpty()) {
            return new ResponseEntity<>("Orders not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(listOrders);
    }

    private static ResponseEntity<? super OrderRespDTO> checking(Optional<OrderRespDTO> orderRespDTO) {
        if (orderRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(orderRespDTO);
        }
        return new ResponseEntity<>("Order not found. No action taken.", HttpStatus.NOT_FOUND);
    }
}
