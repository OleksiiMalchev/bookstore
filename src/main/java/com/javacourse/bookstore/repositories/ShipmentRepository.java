package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Shipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository  extends CrudRepository<Shipment,Long>{
    public List<Shipment> findAll();
}
