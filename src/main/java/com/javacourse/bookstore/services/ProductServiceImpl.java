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
    public List<ProductRespDTOWithWarehouseInfo> getAllProductWithWarehouseInfo() {
        return productRepository.findAllWithInfo()
                .stream()
                .map(mapperForProduct::productToProductRespDTOWithInfo)
                .toList();
    }


    @Override
    public Optional<ProductRespDTO> getProductById(Long idProduct) {
        return productRepository.findById(idProduct)
                .map(mapperForProduct::productToProductRespDTO);
    }

    @Override
    public Optional<ProductRespDTOWithWarehouseInfo> getProductByIdWithWarehouseInfo(Long idProduct) {
        return productRepository.findById(idProduct)
                .map(mapperForProduct::productToProductRespDTOWithInfo);
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
        return productRepository.findById(idProduct)
                .map(p -> {
                    p.setPrice(productReqDTO.getPrice());
                    p.setDescription(productReqDTO.getDescription());
                    return p;
                })
                .map(mapperForProduct::productToProductRespDTO);
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
