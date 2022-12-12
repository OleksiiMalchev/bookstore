package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query("SELECT product from Product product join fetch  product.warehouses")
    List<Product> findAllWithInfo();

    List<Product> findAll();
    @Query("SELECT product from Product product left join fetch  product.warehouses where product.id = :id")
    Optional<Product> findByIdWithInfo(@Param("id") Long idProduct);

}
