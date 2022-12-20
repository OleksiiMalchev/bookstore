package com.javacourse.bookstore.domain.impl;

import com.javacourse.bookstore.domain.dto.WarehouseReqDTO;
import com.javacourse.bookstore.domain.dto.WarehouseRespDTO;
import com.javacourse.bookstore.mappers.MapperForWarehouse;
import com.javacourse.bookstore.repositories.ProductRepository;
import com.javacourse.bookstore.repositories.WarehouseRepository;
import com.javacourse.bookstore.services.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final MapperForWarehouse mapperForWarehouse;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

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


    @Override
    public Optional<WarehouseRespDTO> createWarehouse(WarehouseReqDTO warehouseReqDTO) {
        Long idProduct = warehouseReqDTO.getProductId();
        Integer bookQuantity = warehouseReqDTO.getBookQuantity();
        if (idProduct != null && bookQuantity != null && productRepository.existsById(idProduct)) {
            return mapperForWarehouse.warehouseReqDTOToWarehouse(warehouseReqDTO)
                    .map(warehouseRepository::save)
                    .map(mapperForWarehouse::warehouseToWarehouseRespDTO);
        }
        return Optional.empty();
    }
    @Transactional
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
