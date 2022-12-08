package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.domain.dto.ProductReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<ProductRespDTO> getAllProduct();

    Optional<ProductRespDTO> getProductByWarehouseId(Long idWarehouse);

    Optional<ProductRespDTO> getProductById(Long idProduct);

    Optional<ProductRespDTO> createProduct(ProductReqDTO productReqDTO) throws Exception;

    Optional<ProductRespDTO> updateProduct(Long idProduct, ProductReqDTO productReqDTO);

    Optional<ProductRespDTO> deleteProduct(Long idProduct);
}
