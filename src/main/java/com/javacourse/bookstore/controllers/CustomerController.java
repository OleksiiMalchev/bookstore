package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.CustomerReqDto;
import com.javacourse.bookstore.domain.dto.CustomerRespDto;
import com.javacourse.bookstore.domain.dto.WarehouseRespDTO;
import com.javacourse.bookstore.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers/{id}")
    public ResponseEntity<? super CustomerRespDto> getCustomerById(@PathVariable("id") Long idCustomer) {
        Optional<CustomerRespDto> customerById = customerService.getCustomerById(idCustomer);
        if (customerById.isPresent()) {
            return ResponseEntity.status(200).body(customerById);
        } else {
            return new ResponseEntity<>("Customer not found ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<? super List<CustomerRespDto>> getAllCustomer() {
        List<CustomerRespDto> customerRespDtoList = customerService.getAllCustomer();
        if (customerRespDtoList.isEmpty()) {
            return new ResponseEntity<>("Warehouse not found ", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(200).body(customerRespDtoList);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<? super CustomerRespDto> createCustomer(@RequestBody(required = false)
                                                                  CustomerReqDto customerReqDto) {
        return checkingCreate(customerService.createCustomer(customerReqDto));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<? super CustomerRespDto> updateCustomer(@PathVariable("id") Long idCustomer,
                                                                  @RequestBody(required = false) CustomerReqDto customerReqDto) {
        return checking(customerService.updateCustomer(idCustomer, customerReqDto));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<? super CustomerRespDto> deleteCustomer(@PathVariable("id") Long idCustomer) {
        return checking(customerService.deleteCustomer(idCustomer));
    }


    private static ResponseEntity<? super CustomerRespDto> checkingCreate(Optional<CustomerRespDto> customerRespDto) {
        if (customerRespDto.isPresent()) {
            return ResponseEntity.status(201).body(customerRespDto);
        }
        return new ResponseEntity<>("Customer can not be creating!", HttpStatus.NOT_FOUND);
    }

    private static ResponseEntity<? super CustomerRespDto> checking(Optional<CustomerRespDto> customerRespDto) {
        if (customerRespDto.isPresent()) {
            return ResponseEntity.status(200).body(customerRespDto);
        }
        return new ResponseEntity<>("Customer not found!", HttpStatus.NOT_FOUND);
    }
}
