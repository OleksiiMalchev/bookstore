package com.javacourse.bookstore.services;

import com.javacourse.bookstore.configuration.TestLightConfig;
import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.Product;
import com.javacourse.bookstore.domain.Warehouse;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.domain.dto.ProductReqDTO;
import com.javacourse.bookstore.domain.dto.ProductRespDTO;
import com.javacourse.bookstore.domain.dto.ProductRespDTOWithWarehouseInfo;
import com.javacourse.bookstore.repositories.BookRepository;
import com.javacourse.bookstore.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceUnitTest extends TestLightConfig {
    @Autowired
    private ProductService productService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ProductRepository productRepository;
    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;


    final Long testId = 21L;
    final Long testBookId = 11L;
    final Long testInitialPrice = 100L;
    final Long testPrice = 120L;
    final String testDescription = "Some Book";
    final Book testBook = Book.builder().id(testBookId).title("A Game of Thrones").pages(720).build();
    final Warehouse warehouse = Warehouse.builder().productId(21L).build();
    final List<Warehouse> warehouseList = List.of(warehouse);

    @Test
    void getAllProduct() {
        Product product = Product.builder()
                .bookId(testBookId)
                .book(testBook)
                .initialPrice(testInitialPrice)
                .price(testPrice)
                .description(testDescription)
                .id(testId)
                .warehouses(warehouseList)
                .build();

        BookRespDTO bookRespDTO = BookRespDTO.builder().bookId(11L).build();
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<ProductRespDTO> allProduct = productService.getAllProduct();
        Assertions.assertEquals(1, allProduct.size());
        Assertions.assertEquals(testBookId, allProduct.get(0).getBook().getBookId());
//        Assertions.assertEquals(bookRespDTO, allProduct.get(0).getBook());
        Assertions.assertEquals(testDescription, allProduct.get(0).getDescription());
        Assertions.assertEquals(testId, allProduct.get(0).getId());
        Assertions.assertEquals(testPrice, allProduct.get(0).getPrice());

    }

    @Test
    void getAllProductWithWarehouseInfo() {

        Product product = Product.builder()
                .bookId(testBookId)
                .book(testBook)
                .initialPrice(testInitialPrice)
                .price(testPrice)
                .description(testDescription)
                .id(testId)
                .warehouses(warehouseList)
                .build();
        warehouse.setProduct(product);

        BookRespDTO bookRespDTO = BookRespDTO.builder().bookId(11L).build();
        when(productRepository.findAllWithInfo()).thenReturn(List.of(product));
        List<ProductRespDTOWithWarehouseInfo> allProductWithWarehouseInfo = productService.getAllProductWithWarehouseInfo();
        Assertions.assertEquals(1, allProductWithWarehouseInfo.size());
        Assertions.assertEquals(testBookId, allProductWithWarehouseInfo.get(0).getBook().getBookId());
        //   Assertions.assertEquals(warehouse, allProductWithWarehouseInfo.get(0).getWarehouse());
//        Assertions.assertEquals(bookRespDTO, allProduct.get(0).getBook());
        Assertions.assertEquals(testDescription, allProductWithWarehouseInfo.get(0).getDescription());
        Assertions.assertEquals(testId, allProductWithWarehouseInfo.get(0).getId());
        Assertions.assertEquals(testPrice, allProductWithWarehouseInfo.get(0).getPrice());
    }

    @Test
    void getProductById() {
        Product product = Product.builder()
                .bookId(testBookId)
                .book(testBook)
                .initialPrice(testInitialPrice)
                .price(testPrice)
                .description(testDescription)
                .id(testId)
                .warehouses(warehouseList)
                .build();

        when(productRepository.findById(testId)).thenReturn(Optional.of(product));
        Optional<ProductRespDTO> productById = productService.getProductById(testId);
        Assertions.assertTrue(productById.isPresent());
        Assertions.assertEquals(testBookId, productById.get().getBook().getBookId());
        Assertions.assertEquals(testBook.getTitle(), productById.get().getBook().getTitle());
        Assertions.assertEquals(testBook.getPages(), productById.get().getBook().getPages());
        Assertions.assertEquals(testPrice, productById.get().getPrice());
        Assertions.assertEquals(testDescription, productById.get().getDescription());

    }

    @Test
    void getProductByIdWithWarehouseInfo() {
        Product product = Product.builder()
                .bookId(testBookId)
                .book(testBook)
                .initialPrice(testInitialPrice)
                .price(testPrice)
                .description(testDescription)
                .id(testId)
                .warehouses(warehouseList)
                .build();
        warehouse.setProduct(product);

        when(productRepository.findByIdWithInfo(testId)).thenReturn(Optional.of(product));
        Optional<ProductRespDTOWithWarehouseInfo> productByIdWithWarehouseInfo = productService.getProductByIdWithWarehouseInfo(testId);
        Assertions.assertTrue(productByIdWithWarehouseInfo.isPresent());
        Assertions.assertEquals(testBookId, productByIdWithWarehouseInfo.get().getBook().getBookId());
        Assertions.assertEquals(testBook.getTitle(), productByIdWithWarehouseInfo.get().getBook().getTitle());
        Assertions.assertEquals(testBook.getPages(), productByIdWithWarehouseInfo.get().getBook().getPages());
        Assertions.assertEquals(testPrice, productByIdWithWarehouseInfo.get().getPrice());
        Assertions.assertEquals(testDescription, productByIdWithWarehouseInfo.get().getDescription());
    }

    @Test
    void createProduct() {

        ProductReqDTO productReqDTO = ProductReqDTO.builder()
                .bookId(testBookId)
                .initialPrice(testInitialPrice)
                .description(testDescription)
                .build();

        Product productToSave = Product.builder()
                .id(testId)
                .bookId(testBookId)
                .initialPrice(testInitialPrice)
                .price(testPrice)
                .description(testDescription)
                .build();

        Product productInBase = Product.builder()
                .id(testId)
                .bookId(testBookId)
                .book(testBook)
                .initialPrice(testInitialPrice)
                .price(testPrice)
                .description(testDescription)
                .build();

        when(bookRepository.existsById(testBookId)).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(productToSave);
        when(productRepository.findById(testId)).thenReturn(Optional.ofNullable(productInBase));
        Optional<ProductRespDTO> product = productService.createProduct(productReqDTO);
        verify(productRepository,times(1)).save(productArgumentCaptor.capture());
        Product productValue = productArgumentCaptor.getValue();
        Assertions.assertEquals(testBookId, productValue.getBookId());
        Assertions.assertEquals(testInitialPrice, productValue.getInitialPrice());
        Assertions.assertEquals(testPrice, productValue.getPrice());
        Assertions.assertEquals(testDescription, productValue.getDescription());
        Assertions.assertTrue(product.isPresent());
        Assertions.assertEquals(testId, product.get().getId());
        Assertions.assertEquals(testPrice, product.get().getPrice());
        Assertions.assertEquals(testDescription, product.get().getDescription());
        Assertions.assertEquals(testDescription, product.get().getDescription());

    }

    @Test
    void updateProduct() {
        ProductReqDTO productReqDTO = ProductReqDTO.builder()
                .bookId(testBookId)
                .initialPrice(testInitialPrice)
                .description(testDescription)
                .build();

        Product productInBase = Product.builder()
                .id(testId)
                .bookId(testBookId)
                .book(testBook)
                .initialPrice(testInitialPrice)
                .price(testPrice)
                .description(testDescription)
                .build();


        when(productRepository.findById(testId)).thenReturn(Optional.of(productInBase));
        when(productRepository.existsById(testId)).thenReturn(true);
        Optional<ProductRespDTO> product = productService.updateProduct(testId,productReqDTO);
        verify(productRepository, times(1)).findById(testId);
        Assertions.assertTrue(product.isPresent());
        Assertions.assertEquals(testId, product.get().getId());
        Assertions.assertEquals(testPrice, product.get().getPrice());
        Assertions.assertEquals(testDescription, product.get().getDescription());
        Assertions.assertEquals(testDescription, product.get().getDescription());
        when(productRepository.existsById(testId)).thenReturn(false);
        Optional<ProductRespDTO> productRespDTO = productService.updateProduct(testId, productReqDTO);
        Assertions.assertFalse(productRespDTO.isPresent());
    }


    @Test
    void deleteProduct() {
        Product productInBase = Product.builder()
                .id(testId)
                .bookId(testBookId)
                .book(testBook)
                .initialPrice(testInitialPrice)
                .price(testPrice)
                .description(testDescription)
                .build();

        when(productRepository.findById(testId)).thenReturn(Optional.ofNullable(productInBase));
//        verify(productRepository,times(1)).findById(a;
        Optional<ProductRespDTO> productRespDTO = productService.deleteProduct(testId);
        Assertions.assertTrue(productRespDTO.isPresent());
        Assertions.assertEquals(testId,productRespDTO.get().getId());
        Assertions.assertEquals(testPrice,productRespDTO.get().getPrice());
        Assertions.assertEquals(testDescription,productRespDTO.get().getDescription());
        Optional<ProductRespDTO> productNull  = productService.deleteProduct(null);
        Assertions.assertFalse(productNull.isPresent());
    }
}
