package com.javacourse.bookstore.services;


import com.javacourse.bookstore.domain.dto.CustomerReqDto;
import com.javacourse.bookstore.domain.dto.CustomerRespDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerRespDto> getAllCustomer();

    Optional<CustomerRespDto> getCustomerById(Long idCustomer);

    Optional<CustomerRespDto> createCustomer(CustomerReqDto customerReqDto);

    Optional<CustomerRespDto> updateCustomer(Long idCustomer, CustomerReqDto customerReqDto);

    Optional<CustomerRespDto> deleteCustomer(Long idCustomer);
}
