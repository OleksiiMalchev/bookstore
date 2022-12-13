package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.WarehouseReqDTO;
import com.javacourse.bookstore.domain.dto.WarehouseRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WarehouseService {

    List<WarehouseRespDTO> getAllWarehouse();

    Optional<WarehouseRespDTO> getWarehouseById(Long idWarehouse);

    Optional<WarehouseRespDTO> createWarehouse(WarehouseReqDTO warehouseReqDTO);

    Optional<WarehouseRespDTO> updateWarehouse(Long idWarehouse, WarehouseReqDTO warehouseReqDTO);

    Optional<WarehouseRespDTO> deleteWarehouse(Long idWarehouse);
}
