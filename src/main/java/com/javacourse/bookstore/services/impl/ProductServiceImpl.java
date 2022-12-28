package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.mappers.MapperForProduct;
import com.javacourse.bookstore.domain.Product;
import com.javacourse.bookstore.domain.dto.ProductReqDTO;
import com.javacourse.bookstore.domain.dto.ProductRespDTO;
import com.javacourse.bookstore.domain.dto.ProductRespDTOWithWarehouseInfo;
import com.javacourse.bookstore.repositories.BookRepository;
import com.javacourse.bookstore.repositories.ProductRepository;
import com.javacourse.bookstore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final MapperForProduct mapperForProduct;
    private final ProductRepository productRepository;
    private final BookRepository bookRepository;

    @Override
    public List<ProductRespDTO> getAllProduct() {
        return productRepository.findAll()
                .stream()
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
        return productRepository.findByIdWithInfo(idProduct)
                .map(mapperForProduct::productToProductRespDTOWithInfo);
    }

    @Override
    public Optional<ProductRespDTO> createProduct(ProductReqDTO productReqDTO) {
        Long bookId = productReqDTO.getBookId();
        if (bookId != null && bookRepository.existsById(bookId)) {
            Optional<Product> product = mapperForProduct.productReqDTOToProduct(productReqDTO)
                    .map(productRepository::save);
            return productRepository.findById(product.get().getId())
                    .map(mapperForProduct::productToProductRespDTO);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<ProductRespDTO> updateProduct(Long idProduct, ProductReqDTO productReqDTO) {
        Optional<Product> productById = productRepository.findById(idProduct);
        if (productRepository.existsById(idProduct)) {
            return productById
                    .map(p -> {
                        p.setDescription(productReqDTO.getDescription());
                        p.setInitialPrice(productReqDTO.getInitialPrice());
                        p.setPrice((long) (productReqDTO.getInitialPrice() * 1.2));
                        return p;
                    })
                    .map(mapperForProduct::productToProductRespDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProductRespDTO> deleteProduct(Long idProduct) {
        Optional<ProductRespDTO> productRespDTO = productRepository
                .findById(idProduct)
                .map(mapperForProduct::productToProductRespDTO);
        if (productRespDTO.isPresent()) {
            productRepository.deleteById(idProduct);
        }
        return productRespDTO;
    }
}
