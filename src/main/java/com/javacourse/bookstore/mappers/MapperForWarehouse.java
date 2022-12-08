package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.mappers.domain.Warehouse;
import com.javacourse.bookstore.mappers.domain.dto.WarehouseReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.WarehouseRespDTO;
import com.javacourse.bookstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MapperForWarehouse {
    private final ProductRepository productRepository;


    public Optional<Warehouse> warehouseReqDTOToWarehouse(WarehouseReqDTO warehouseReqDTO) {
        return Optional.ofNullable(warehouseReqDTO)
                .stream()
                .findAny()
                .map(w -> Warehouse
                        .builder()
                        .product(productRepository.findById(warehouseReqDTO.getProductId()).get())
                        .bookQuantity(w.getBookQuantity())
                        .initial_price(w.getInitialPrice())
                        .build());
    }
    public WarehouseRespDTO warehouseToWarehouseRespDTO(Warehouse warehouse) {
        return Optional.ofNullable(warehouse)
                .stream()
                .findAny()
                .map(w -> WarehouseRespDTO.builder()
                        .productId(warehouse.getProduct().getId())
                        .bookQuantity(w.getBookQuantity())
                        .build())
                .orElse(null);
    }
}