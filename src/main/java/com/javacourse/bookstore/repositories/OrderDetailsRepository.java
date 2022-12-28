package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
    List<OrderDetails> findAll();
}
