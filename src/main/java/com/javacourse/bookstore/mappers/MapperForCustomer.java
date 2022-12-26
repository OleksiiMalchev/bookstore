package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Customer;
import com.javacourse.bookstore.domain.dto.CustomerReqDto;
import com.javacourse.bookstore.domain.dto.CustomerRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MapperForCustomer {
    public Optional<Customer> customerReqDtoToCustomer(CustomerReqDto customerReqDto){
        return Optional.ofNullable(customerReqDto)
                .map(c->Customer.builder()
                        .firstName(customerReqDto.getFirstName())
                        .lastName(customerReqDto.getLastName())
                        .dateOfBirth(customerReqDto.getDateOfBirth())
                        .gender(customerReqDto.getGender())
                        .build());
    }
    public CustomerRespDto customerToCustomerRespDto(Customer customer){
        return Optional.ofNullable(customer)
                .map(c->CustomerRespDto.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .dateOfBirth(customer.getDateOfBirth())
                        .gender(customer.getGender())
                        .build())
                .orElse(null);
    }
}
