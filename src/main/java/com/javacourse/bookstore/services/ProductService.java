package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.domain.dto.ProductReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTOWithWarehouseInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<ProductRespDTO> getAllProduct();

    List<ProductRespDTOWithWarehouseInfo> getAllProductWithWarehouseInfo();

    Optional<ProductRespDTO> getProductById(Long idProduct);
    Optional<ProductRespDTOWithWarehouseInfo> getProductByIdWithWarehouseInfo(Long idProduct);

    Optional<ProductRespDTO> createProduct(ProductReqDTO productReqDTO) throws Exception;

    Optional<ProductRespDTO> updateProduct(Long idProduct, ProductReqDTO productReqDTO);

    Optional<ProductRespDTO> deleteProduct(Long idProduct);
}
