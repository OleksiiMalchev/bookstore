package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.mappers.domain.dto.ProductReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTOWithWarehouseInfo;
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
    public ResponseEntity<? super ProductRespDTOWithWarehouseInfo> getProductById(@PathVariable("id") Long idProduct) {
        Optional<ProductRespDTO> productById = productService.getProductById(idProduct);
        if (productById.isPresent()) {
            return ResponseEntity.status(200).body(productById);
        } else {
            return new ResponseEntity<>("Product not found ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/productWarehouse/{idProduct}")
    public ResponseEntity<? super ProductRespDTOWithWarehouseInfo> getProductByWarehouseId(@PathVariable("idProduct") Long idProduct) {
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
        Optional<ProductRespDTO> newProduct = productService.createProduct(productReqDTO);
        if (newProduct.isPresent()) {
            return ResponseEntity.status(201).body(newProduct);
        }
        return new ResponseEntity<>("Invalid request. Product not create", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/products/{idProduct}")
    public ResponseEntity<? super ProductRespDTO> update(@PathVariable("idProduct") Long idProduct,
                                                        @RequestBody(required = false) ProductReqDTO productReqDTO) {
        Optional<ProductRespDTO> productUpdate = productService.updateProduct(idProduct, productReqDTO);
        if (productUpdate.isPresent()) {
            return ResponseEntity.status(200).body(productUpdate);
        }
        return new ResponseEntity<>("Invalid request. Product not update", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/products/{idProduct}")
    public ResponseEntity<? super ProductRespDTO> delete(@PathVariable("idProduct") Long idProduct)  {
        Optional<ProductRespDTO> productDelete = productService.deleteProduct(idProduct);
        if (productDelete.isPresent()) {
            return ResponseEntity.status(200).body(productDelete);
        }
        return new ResponseEntity<>("Product not found ", HttpStatus.NOT_FOUND);
    }
}
