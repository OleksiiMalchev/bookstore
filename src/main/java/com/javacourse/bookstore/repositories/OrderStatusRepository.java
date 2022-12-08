package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Customer;
import com.javacourse.bookstore.mappers.domain.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus,Long>{
}

