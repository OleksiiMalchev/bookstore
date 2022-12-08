package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
    @Query("SELECT author from Author author join  author.books b  where b.id = :id")
    Long getInitialPriceByProductId(@Param("id") Long idBook);
}
