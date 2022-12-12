package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
    List<Warehouse> findAll();

}
