package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.mappers.domain.Warehouse;
import com.javacourse.bookstore.mappers.domain.dto.*;
import com.javacourse.bookstore.services.WarehouseService;
import com.javacourse.bookstore.services.WarehouseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping("/warehouses/{id}")
    public ResponseEntity<? super WarehouseRespDTO> getWarehouseById(@PathVariable("id") Long idWarehouse) {
        Optional<WarehouseRespDTO> warehouseById = warehouseService.getWarehouseById(idWarehouse);
        if (warehouseById.isPresent()) {
            return ResponseEntity.status(200).body(warehouseById);
        } else {
            return new ResponseEntity<>("Warehouse not found ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/warehouses")
    public ResponseEntity<? super List<WarehouseRespDTO>> getAllWarehouse() throws SQLException {
        List<WarehouseRespDTO> allWarehouse = warehouseService.getAllWarehouse();
        if (allWarehouse.isEmpty()) {
            return new ResponseEntity<>("Warehouse not found ", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(200).body(allWarehouse);
        }
    }
    @PostMapping("/warehouses")
    public ResponseEntity<? super WarehouseRespDTO> createWarehouse(@RequestBody(required = false)
                                                                    WarehouseReqDTO warehouseReqDTO) throws Exception {
        Optional<WarehouseRespDTO> newWarehouse = warehouseService.createWarehouse(warehouseReqDTO);
        if (newWarehouse.isPresent()) {
            return ResponseEntity.status(201).body(warehouseReqDTO);
        }
        return new ResponseEntity<>("Invalid request. Warehouse not create", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/warehouses/{id}")
    public ResponseEntity<? super WarehouseRespDTO> update(@PathVariable("id") Long id,
                                                         @RequestBody(required = false) WarehouseReqDTO warehouseReqDTO) {
        Optional<WarehouseRespDTO> warehouseUpdate = warehouseService.updateWarehouse(id, warehouseReqDTO);
        if (warehouseUpdate.isPresent()) {
            return ResponseEntity.status(200).body(warehouseUpdate);
        }
        return new ResponseEntity<>("Invalid request. Warehouse not update", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/warehouses/{id}")
    public ResponseEntity<? super WarehouseRespDTO> delete(@PathVariable("id") Long idWarehouse)  {
        Optional<WarehouseRespDTO> warehouseDelete = warehouseService.deleteWarehouse(idWarehouse);
        if (warehouseDelete.isPresent()) {
            return ResponseEntity.status(200).body(warehouseDelete);
        }
        return new ResponseEntity<>("Warehouse not found ", HttpStatus.NOT_FOUND);
    }
}
