package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
    List<Warehouse> findAll();

   //@Query("SELECT w FROM Warehouse JOIN w.product wp WHERE product.id = :id")

    @Query(value = "SELECT * FROM warehouse INNER JOIN product ON product.id = warehouse.product_id WHERE product_id = :id",nativeQuery = true)
    Optional<Warehouse> findWarehouseByProductId(@Param("id") Long productId);
}
