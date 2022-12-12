package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.MapperForWarehouse;
import com.javacourse.bookstore.mappers.domain.dto.WarehouseReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.WarehouseRespDTO;
import com.javacourse.bookstore.repositories.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final MapperForWarehouse mapperForWarehouse;
    private final WarehouseRepository warehouseRepository;

    public List<WarehouseRespDTO> getAllWarehouse() {
        return warehouseRepository.findAll()
                .stream()
                .map(mapperForWarehouse::warehouseToWarehouseRespDTO)
                .toList();
    }

    public Optional<WarehouseRespDTO> getWarehouseById(Long idWarehouse) {
        return warehouseRepository.findById(idWarehouse)
                .map(mapperForWarehouse::warehouseToWarehouseRespDTO);
    }

    public Optional<WarehouseRespDTO> createWarehouse(WarehouseReqDTO warehouseReqDTO) {
        Long idProduct = warehouseReqDTO.getProductId();
        Integer bookQuantity = warehouseReqDTO.getBookQuantity();
        if (idProduct == null || bookQuantity == null) {
            return Optional.empty();
        }
        return mapperForWarehouse.warehouseReqDTOToWarehouse(warehouseReqDTO)
                .map(warehouseRepository::save)
                .map(mapperForWarehouse::warehouseToWarehouseRespDTO);

    }

    public Optional<WarehouseRespDTO> updateWarehouse(Long idWarehouse, WarehouseReqDTO warehouseReqDTO) {
        return warehouseRepository.findById(idWarehouse)
                .map(warehouse -> {
                    warehouse.setBookQuantity(warehouseReqDTO.getBookQuantity());
                    return warehouse;
                })
                .map(mapperForWarehouse::warehouseToWarehouseRespDTO);
    }

    public Optional<WarehouseRespDTO> deleteWarehouse(Long idWarehouse) {
        Optional<WarehouseRespDTO> warehouseRespDTO = warehouseRepository.findById(idWarehouse)
                .map(mapperForWarehouse::warehouseToWarehouseRespDTO);
        if (warehouseRespDTO.isPresent()) {
            warehouseRepository.deleteById(idWarehouse);
        }
        return warehouseRespDTO;
    }
}
