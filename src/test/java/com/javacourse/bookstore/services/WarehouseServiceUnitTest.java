package com.javacourse.bookstore.services;

import com.javacourse.bookstore.configuration.TestLightConfig;
import com.javacourse.bookstore.domain.Warehouse;
import com.javacourse.bookstore.domain.dto.WarehouseReqDTO;
import com.javacourse.bookstore.domain.dto.WarehouseRespDTO;
import com.javacourse.bookstore.repositories.ProductRepository;
import com.javacourse.bookstore.repositories.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WarehouseServiceUnitTest extends TestLightConfig {
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Captor
    private ArgumentCaptor<Warehouse> warehouseArgumentCaptor;

    @Test
    void createWarehouse() {


        final Long testWarehouseProductId = 10L;
        final Integer testWarehouseBookQuantity = 100;

        WarehouseReqDTO warehouseReqDTO = WarehouseReqDTO.builder()
                .productId(testWarehouseProductId)
                .bookQuantity(testWarehouseBookQuantity)
                .build();

        Warehouse warehouseToSave = Warehouse.builder()
                .productId(testWarehouseProductId)
                .bookQuantity(testWarehouseBookQuantity)
                .build();

        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouseToSave);
        when(productRepository.existsById(testWarehouseProductId)).thenReturn(true);
        Optional<WarehouseRespDTO> warehouse = warehouseService.createWarehouse(warehouseReqDTO);
        verify(warehouseRepository, times(1)).save(warehouseArgumentCaptor.capture());
        Warehouse warehouseEntity = warehouseArgumentCaptor.getValue();

        Assertions.assertEquals(testWarehouseProductId, warehouseEntity.getProductId());
        Assertions.assertEquals(testWarehouseBookQuantity, warehouseEntity.getBookQuantity());
        Assertions.assertTrue(warehouse.isPresent());
        Assertions.assertEquals(testWarehouseProductId, warehouse.get().getProductId());
        Assertions.assertEquals(testWarehouseBookQuantity, warehouse.get().getBookQuantity());
    }

}
