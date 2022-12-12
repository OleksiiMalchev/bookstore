package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.ProductReqDTO;
import com.javacourse.bookstore.domain.dto.ProductRespDTO;
import com.javacourse.bookstore.domain.dto.ProductRespDTOWithWarehouseInfo;
import com.javacourse.bookstore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<? super ProductRespDTO> getProductById(@PathVariable("id") Long idProduct) {
        return checking(productService.getProductById(idProduct));
    }

    @GetMapping("/productWarehouse/{idProduct}")
    public ResponseEntity<? super ProductRespDTOWithWarehouseInfo> getProductByIdWithDetails(@PathVariable("idProduct") Long idProduct) {
        Optional<ProductRespDTOWithWarehouseInfo> productInfo = productService.getProductByIdWithWarehouseInfo(idProduct);
        if (productInfo.isPresent()) {
            return ResponseEntity.status(200).body(productInfo);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<? super List<ProductRespDTO>> getAllProduct() throws SQLException {
        List<ProductRespDTO> allProduct = productService.getAllProduct();
        if (allProduct.isEmpty()) {
            return new ResponseEntity<>("Products not found", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(200).body(allProduct);
        }
    }

    @GetMapping("/products/info")
    public ResponseEntity<? super List<ProductRespDTOWithWarehouseInfo>> getAllProductWithWarehouseInfo() throws SQLException {
        List<ProductRespDTOWithWarehouseInfo> allProductWithWarehouseInfo = productService.getAllProductWithWarehouseInfo();
        if (allProductWithWarehouseInfo.isEmpty()) {
            return new ResponseEntity<>("Products not found", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(200).body(allProductWithWarehouseInfo);
        }
    }


    @PostMapping("/products")
    public ResponseEntity<? super ProductRespDTO> createProduct(@RequestBody(required = false)
                                                                ProductReqDTO productReqDTO) throws Exception {
        return checking(productService.createProduct(productReqDTO));
    }

    @PutMapping("/products/{idProduct}")
    public ResponseEntity<? super ProductRespDTO> update(@PathVariable("idProduct") Long idProduct,
                                                         @RequestBody(required = false) ProductReqDTO productReqDTO) {
        return checking(productService.updateProduct(idProduct, productReqDTO));
    }

    @DeleteMapping("/products/{idProduct}")
    public ResponseEntity<? super ProductRespDTO> delete(@PathVariable("idProduct") Long idProduct) {
        return checking(productService.deleteProduct(idProduct));
    }

    private static ResponseEntity<? super ProductRespDTO> checking(Optional<ProductRespDTO> productRespDTO) {
        if (productRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(productRespDTO);
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
}

