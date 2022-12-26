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

import java.util.List;
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

    final Long testWarehouseId = 1L;
    final Long testWarehouseProductId = 10L;
    final Integer testWarehouseBookQuantity = 100;
    final Long testWarehouseIdSecond = 2L;
    final Long testWarehouseProductIdSecond = 11L;
    final Integer testWarehouseBookQuantitySecond = 111;

    @Test
    void findAllWarehouse() {
        Warehouse warehouseFirst = Warehouse.builder()
                .productId(testWarehouseProductId)
                .bookQuantity(testWarehouseBookQuantity)
                .build();
        Warehouse warehouseSecond = Warehouse.builder()
                .productId(testWarehouseProductIdSecond)
                .bookQuantity(testWarehouseBookQuantitySecond)
                .build();

        when(warehouseRepository.findAll()).thenReturn(List.of(warehouseFirst, warehouseSecond));
        List<WarehouseRespDTO> warehouseAll = warehouseService.getAllWarehouse();
        verify(warehouseRepository, times(1)).findAll();
        Assertions.assertEquals(warehouseAll.size(), 2);
        Assertions.assertEquals(testWarehouseProductId, warehouseAll.get(0).getProductId());
        Assertions.assertEquals(testWarehouseBookQuantity, warehouseAll.get(0).getBookQuantity());
        Assertions.assertEquals(testWarehouseProductIdSecond, warehouseAll.get(1).getProductId());
        Assertions.assertEquals(testWarehouseBookQuantitySecond, warehouseAll.get(1).getBookQuantity());
    }

    @Test
    void getWarehouseById() {
        Warehouse warehouseToFind = Warehouse.builder()
                .id(testWarehouseId)
                .productId(testWarehouseProductId)
                .bookQuantity(testWarehouseBookQuantity)
                .build();

        when(warehouseRepository.findById(testWarehouseId)).thenReturn(Optional.ofNullable(warehouseToFind));
        Optional<WarehouseRespDTO> warehouse = warehouseService.getWarehouseById(testWarehouseId);
        verify(warehouseRepository, times(1)).findById(testWarehouseId);
        Assertions.assertTrue(warehouse.isPresent());
        Assertions.assertEquals(testWarehouseProductId, warehouse.get().getProductId());
        Assertions.assertEquals(testWarehouseBookQuantity, warehouse.get().getBookQuantity());
    }

    @Test
    void createWarehouse() {
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

    @Test
    void updateWarehouse() {
        WarehouseReqDTO warehouseReqDTO = WarehouseReqDTO.builder()
                .bookQuantity(testWarehouseBookQuantitySecond)
                .build();
        Warehouse warehouseOrigin = Warehouse.builder()
                .id(testWarehouseId)
                .productId(testWarehouseProductId)
                .bookQuantity(testWarehouseBookQuantity)
                .build();
        Warehouse warehouseChange = Warehouse.builder()
                .id(testWarehouseId)
                .productId(testWarehouseProductIdSecond)
                .bookQuantity(testWarehouseBookQuantitySecond)
                .build();
        when(warehouseRepository.existsById(testWarehouseId)).thenReturn(true);
        when(warehouseRepository.findById(testWarehouseId)).thenReturn(Optional.ofNullable(warehouseOrigin));
        when(warehouseRepository.save(warehouseChange)).thenReturn(warehouseChange);
        when(warehouseRepository.findById(testWarehouseId)).thenReturn(Optional.ofNullable(warehouseChange));
        Optional<WarehouseRespDTO> warehouse = warehouseService.updateWarehouse(testWarehouseId, warehouseReqDTO);

        Assertions.assertTrue(warehouse.isPresent());
        Assertions.assertEquals(testWarehouseBookQuantitySecond, warehouse.get().getBookQuantity());
    }

    @Test
    void deleteWarehouse() {
        Warehouse warehouse = Warehouse.builder()
                .id(testWarehouseId)
                .productId(testWarehouseProductId)
                .bookQuantity(testWarehouseBookQuantity)
                .build();

        when(warehouseRepository.existsById(testWarehouseId)).thenReturn(true);
        warehouseRepository.deleteById(testWarehouseId);
        when(warehouseRepository.existsById(testWarehouseId)).thenReturn(false);
        Optional<WarehouseRespDTO> warehouseDelete = warehouseService.deleteWarehouse(warehouse.getId());
        Assertions.assertFalse(warehouseDelete.isPresent());
    }
}
