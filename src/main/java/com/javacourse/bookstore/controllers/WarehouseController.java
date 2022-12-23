package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.WarehouseReqDTO;
import com.javacourse.bookstore.domain.dto.WarehouseRespDTO;
import com.javacourse.bookstore.services.WarehouseService;
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
    public ResponseEntity<? super WarehouseRespDTO> createWarehouse(@RequestBody(required = false) WarehouseReqDTO warehouseReqDTO) {
        if(warehouseReqDTO!=null){
            return checking(warehouseService.createWarehouse(warehouseReqDTO));
        }
       return   new ResponseEntity<>("Warehouse is null", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/warehouses/{id}")
    public ResponseEntity<? super WarehouseRespDTO> update(@PathVariable("id") Long id,
                                                           @RequestBody(required = false) WarehouseReqDTO warehouseReqDTO) {
        return checking(warehouseService.updateWarehouse(id, warehouseReqDTO));
    }

    @DeleteMapping("/warehouses/{id}")
    public ResponseEntity<? super WarehouseRespDTO> delete(@PathVariable("id") Long idWarehouse) {
        return checking(warehouseService.deleteWarehouse(idWarehouse));
    }

    private static ResponseEntity<? super WarehouseRespDTO> checking(Optional<WarehouseRespDTO> warehouseRespDTO) {
        if (warehouseRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(warehouseRespDTO);
        }
        return new ResponseEntity<>("Warehouse not found or can not be creating!", HttpStatus.NOT_FOUND);
    }
}
