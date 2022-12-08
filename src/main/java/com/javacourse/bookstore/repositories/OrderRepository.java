package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepository extends CrudRepository<Order,Long>{
}
