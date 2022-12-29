package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.domain.Customer;
import com.javacourse.bookstore.domain.dto.CustomerReqDto;
import com.javacourse.bookstore.domain.dto.CustomerRespDto;
import com.javacourse.bookstore.mappers.MapperForCustomer;
import com.javacourse.bookstore.repositories.CustomerRepository;
import com.javacourse.bookstore.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final MapperForCustomer mapperForCustomer;

    @Override
    public List<CustomerRespDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(mapperForCustomer::customerToCustomerRespDto)
                .toList();
    }

    @Override
    public Optional<CustomerRespDto> getCustomerById(Long idCustomer) {
        return customerRepository.findById(idCustomer)
                .map(mapperForCustomer::customerToCustomerRespDto);
    }

    @Override
    public Optional<CustomerRespDto> createCustomer(CustomerReqDto customerReqDto) {
        return mapperForCustomer.customerReqDtoToCustomer(customerReqDto)
                .map(customerRepository::save)
                .map(mapperForCustomer::customerToCustomerRespDto);
    }

    @Transactional
    @Override
    public Optional<CustomerRespDto> updateCustomer(Long idCustomer, CustomerReqDto customerReqDto) {
        Optional<Customer> customer = customerRepository.findById(idCustomer);
        if (customerRepository.existsById(idCustomer)) {
            return customer
                    .map(c -> {
                        c.setFirstName(customerReqDto.getFirstName());
                        c.setLastName(customerReqDto.getLastName());
                        c.setDateOfBirth(customerReqDto.getDateOfBirth());
                        c.setGender(customerReqDto.getGender());
                        return c;
                    }).map(mapperForCustomer::customerToCustomerRespDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<CustomerRespDto> deleteCustomer(Long idCustomer) {
        Optional<CustomerRespDto> customerRespDto = customerRepository.findById(idCustomer)
                .map(mapperForCustomer::customerToCustomerRespDto);
        if (customerRespDto.isPresent()) {
            customerRepository.deleteById(idCustomer);
        }
        return customerRespDto;
    }
}
