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
        return StreamSupport.stream(warehouseRepository.findAll().spliterator(), false)
                .toList()
                .stream()
                .map(mapperForWarehouse::warehouseToWarehouseRespDTO)
                .toList();
    }

    public Optional<WarehouseRespDTO> getWarehouseById(Long idWarehouse) {
        return warehouseRepository.findById(idWarehouse)
                .map(mapperForWarehouse::warehouseToWarehouseRespDTO);
    }

    public Optional<WarehouseRespDTO> createWarehouse(WarehouseReqDTO warehouseReqDTO) throws Exception {
        Long idProduct = warehouseReqDTO.getProductId();
        Integer bookQuantity = warehouseReqDTO.getBookQuantity();
        Long initialPrice = warehouseReqDTO.getInitialPrice();
        if (idProduct == null || bookQuantity == null || initialPrice == null) {
            throw new Exception("Can not create warehouse without product, book quantity and initial price.");
        }
        return mapperForWarehouse.warehouseReqDTOToWarehouse(warehouseReqDTO)
                .map(warehouseRepository::save)
                .map(mapperForWarehouse::warehouseToWarehouseRespDTO);

    }

    public Optional<WarehouseRespDTO> updateWarehouse(WarehouseReqDTO warehouseReqDTO, Long idWarehouse) {
        return warehouseRepository.findById(idWarehouse)
                .map(warehouse -> {
                    warehouse.setInitialPrice(warehouseReqDTO.getInitialPrice());
                    warehouse.setBookQuantity(warehouse.getBookQuantity());
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
