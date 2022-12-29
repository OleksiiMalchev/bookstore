package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.OrderDetailsReqDTO;
import com.javacourse.bookstore.domain.dto.OrderDetailsRespDTO;
import com.javacourse.bookstore.services.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @GetMapping("/orderDetails")
    public ResponseEntity<? super List<OrderDetailsRespDTO>> getAllOrderDetails() {
        return checkingOrders(orderDetailsService.getAllOrderDetails());
    }

    @GetMapping("/orderDetails/{id}")
    public ResponseEntity<? super OrderDetailsRespDTO> getOrderById(@PathVariable("id") Long idOrderDetails) {
        return checking(orderDetailsService.getOrderDetailsById(idOrderDetails));
    }

//    @PostMapping("/orderDetails")
//    public ResponseEntity<? super OrderDetailsRespDTO> create(@RequestBody(required = false) OrderDetailsReqDTO orderDetailsReqDTO) {
//        Optional<OrderDetailsRespDTO> orderDetails = orderDetailsService.createOrderDetails(orderDetailsReqDTO);
//        if (orderDetails.isPresent()) {
//            return ResponseEntity.status(201).body(orderDetails);
//        }
//        return new ResponseEntity<>("OrderDetails not found. No action taken.", HttpStatus.NOT_FOUND);
//    }

    @PutMapping("/orderDetails/{id}")
    public ResponseEntity<? super OrderDetailsRespDTO> update(@PathVariable("id") Long id,
                                                              @RequestBody(required = false) OrderDetailsReqDTO orderDetailsReqDTO) {
        return checking(orderDetailsService.updateOrderDetails(id, orderDetailsReqDTO));

    }


    @DeleteMapping("/orderDetails/{id}")
    public ResponseEntity<? super OrderDetailsRespDTO> delete(@PathVariable("id") Long id) {
        return checking(orderDetailsService.deleteOrderDetails(id));

    }

    private static ResponseEntity<? super List<OrderDetailsRespDTO>> checkingOrders(List<OrderDetailsRespDTO> listOrderDetails) {
        if (listOrderDetails.isEmpty()) {
            return new ResponseEntity<>("OrderDetails not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(listOrderDetails);
    }

    private static ResponseEntity<? super OrderDetailsRespDTO> checking(Optional<OrderDetailsRespDTO> orderDetailsRespDTO) {
        if (orderDetailsRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(orderDetailsRespDTO);
        }
        return new ResponseEntity<>("Order not found. No action taken.", HttpStatus.NOT_FOUND);
    }
}
