package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
