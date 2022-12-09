package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query("SELECT product from Product product join  product.warehouses")
    List<Product> findAllWithInfo();

}
