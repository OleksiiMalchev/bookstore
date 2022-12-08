package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.domain.dto.WarehouseReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.WarehouseRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WarehouseService {

    List<WarehouseRespDTO> getAllWarehouse();

    Optional<WarehouseRespDTO> getWarehouseById(Long idWarehouse);

    Optional<WarehouseRespDTO> createWarehouse(WarehouseReqDTO warehouseReqDTO) throws Exception;

    Optional<WarehouseRespDTO> updateWarehouse(WarehouseReqDTO warehouseReqDTO, Long idWarehouse);

    Optional<WarehouseRespDTO> deleteWarehouse(Long idWarehouse);
}