package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.mappers.domain.Product;
import com.javacourse.bookstore.mappers.domain.dto.ProductReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTOWithWarehouseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MapperForProduct {

    private final MapperForBook mapperForBook;
    private final MapperForWarehouse mapperForWarehouse;

    public Optional<Product> productReqDTOToProduct(ProductReqDTO productReqDTO) {
        return Optional.ofNullable(productReqDTO)
                .map(p -> Product
                        .builder()
                        .bookId(p.getBookId())
                        .initialPrice(p.getInitialPrice())
                        .price((long) (p.getInitialPrice()*1.2))
                        .description(p.getDescription())
                        .build());
    }

    public ProductRespDTO productToProductRespDTO(Product product) {
        if(product!=null) {
            return ProductRespDTO.builder()
                    .id(product.getId())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .book(mapperForBook.toBookRespDTO(product.getBook()))
                    .build();
        }
        return null;
    }

    public ProductRespDTOWithWarehouseInfo productToProductRespDTOWithInfo(Product product) {
        if(product!=null){
            return ProductRespDTOWithWarehouseInfo.builder()
                    .id(product.getId())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .book(mapperForBook.toBookRespDTO(product.getBook()))
                    .warehouse(product.getWarehouses()
                            .stream()
                            .map(mapperForWarehouse::warehouseToWarehouseRespDTO)
                            .filter(w -> w.getProductId().equals(product.getId()))
                            .findAny().orElse(null))
                    .build();
        }
        return null;
    }
}

