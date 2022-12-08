package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Customer;
import com.javacourse.bookstore.mappers.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
