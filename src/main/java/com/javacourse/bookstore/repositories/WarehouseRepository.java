package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Warehouse;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

}
