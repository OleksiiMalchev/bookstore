package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTO;
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
        Optional<ProductRespDTO> productById = productService.getProductById(idProduct);
        if (productById.isPresent()) {
            return ResponseEntity.status(200).body(productById);
        } else {
            return new ResponseEntity<>("Product not found ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/productWarehouse/{idWarehouse}")
    public ResponseEntity<? super ProductRespDTO> getProductByWarehouseId(@PathVariable("idWarehouse") Long idWarehouse) {
        Optional<ProductRespDTO> productByWarehouseId = productService.getProductByWarehouseId(idWarehouse);
        if (productByWarehouseId.isPresent()) {
            return ResponseEntity.status(200).body(productByWarehouseId);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<? super List<AuthorRespDTO>> getAllProduct() throws SQLException {
        List<ProductRespDTO> allProduct = productService.getAllProduct();
        if (allProduct.isEmpty()) {
            return new ResponseEntity<>("Products not found ", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(200).body(allProduct);
        }
    }

//    @GetMapping("/authorWith/{idAuthor}")
//    public ResponseEntity<? super AuthorRespDTOWithBooks> getAuthorWithDetails(@PathVariable("idAuthor") Long idAuthor) {
//        Optional<AuthorRespDTOWithBooks> authorWithDetails = authorService.getAuthorWithDetails(idAuthor);
//        if (authorWithDetails.isPresent()) {
//            return ResponseEntity.status(200).body(authorWithDetails);
//        }
//        return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);
//    }
//
    @PostMapping("/products")
    public ResponseEntity<? super ProductRespDTO> createProduct(@RequestBody(required = false)
                                                                    ProductReqDTO productReqDTO) throws Exception {
        Optional<ProductRespDTO> newProduct = productService.createProduct(productReqDTO);
        if (newProduct.isPresent()) {
            return ResponseEntity.status(201).body(newProduct);
        }
        return new ResponseEntity<>("Invalid request. Product not create", HttpStatus.NOT_FOUND);

    }
//
//    @PutMapping("/authors/{id}")
//    public ResponseEntity<? super AuthorRespDTO> update(@PathVariable("id") Long id,
//                                                        @RequestBody(required = false) AuthorReqDTO authorReqDTO) {
//        Optional<AuthorRespDTO> authorRespDTO = authorService.updateAuthor(id, authorReqDTO);
//        if (authorRespDTO.isPresent()) {
//            return ResponseEntity.status(200).body(authorRespDTO);
//        }
//        return new ResponseEntity<>("Invalid request. Author not update", HttpStatus.NOT_FOUND);
//    }
//
    @DeleteMapping("/products/{idProduct}")
    public ResponseEntity<? super AuthorRespDTO> delete(@PathVariable("idProduct") Long idProduct)  {
        Optional<ProductRespDTO> productDelete = productService.deleteProduct(idProduct);
        if (productDelete.isPresent()) {
            return ResponseEntity.status(200).body(productDelete);
        }
        return new ResponseEntity<>("Product not found ", HttpStatus.NOT_FOUND);
    }
}
