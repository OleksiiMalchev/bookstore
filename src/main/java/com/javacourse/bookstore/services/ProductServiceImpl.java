package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.MapperForProduct;
import com.javacourse.bookstore.mappers.domain.dto.*;
import com.javacourse.bookstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final MapperForProduct mapperForProduct;
    private final ProductRepository productRepository;

    @Override
    public List<ProductRespDTO> getAllProduct() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(mapperForProduct::productToProductRespDTO)
                .toList();
    }

    @Override
    public Optional<ProductRespDTO> getProductByWarehouseId(Long idWarehouse) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductRespDTO> getProductById(Long idProduct) {
        return productRepository.findById(idProduct)
                .map(mapperForProduct::productToProductRespDTO);
    }

    @Override
    public Optional<ProductRespDTO> createProduct(ProductReqDTO productReqDTO) throws Exception {
        Long bookId = productReqDTO.getBookId();
        if (bookId == null) {
            throw new Exception("Cant create product without book");
        } else {
            return mapperForProduct.productReqDTOToProduct(productReqDTO)
                    .map(productRepository::save)
                    .map(mapperForProduct::productToProductRespDTO);
        }
    }

    @Override
    public Optional<ProductRespDTO> updateProduct(Long idProduct, ProductReqDTO productReqDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductRespDTO> deleteProduct(Long idProduct) {
        Optional<ProductRespDTO> productRespDTO = productRepository.findById(idProduct).map(mapperForProduct::productToProductRespDTO);
        if (productRespDTO.isPresent()) {
            productRepository.deleteById(idProduct);
        }
        return productRespDTO;
    }
}
