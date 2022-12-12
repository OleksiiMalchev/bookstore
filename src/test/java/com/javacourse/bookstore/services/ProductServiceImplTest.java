package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.MapperForProduct;
import com.javacourse.bookstore.mappers.domain.Book;
import com.javacourse.bookstore.mappers.domain.Product;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTO;
import com.javacourse.bookstore.repositories.BookRepository;
import com.javacourse.bookstore.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class ProductServiceImplTest  {
    @Autowired
    private ProductServiceImpl productService;
    @MockBean
    private MapperForProduct mapperForProduct;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private BookRepository bookRepository;



    @Test
    void getAllProduct() {
        Book firstBook = Book.builder().authorId(555L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").barCode(1124).id(314L).build();
        BookRespDTO build = BookRespDTO.builder().title("The Day's Play")
                .cover("soft").publishingHouse("XZ").barCode(1124).build();
        Book secondBook = Book.builder().authorId(555L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").barCode(1124).id(314L).build();
        Book thirdBook = Book.builder().authorId(555L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").barCode(1124).id(314L).build();
        Product firstProduct = Product.builder().initialPrice(10L).book(firstBook).description("firstBook").build();
        Product secondProduct = Product.builder().initialPrice(20L).book(secondBook).description("secondBook").build();
        Product thirdProduct = Product.builder().initialPrice(30L).book(thirdBook).description("thirdBook").build();
        Mockito.when(productRepository.findAll())
                .thenReturn(List.of(firstProduct, secondProduct, thirdProduct));
        List<ProductRespDTO> allProduct = productService.getAllProduct();
        Assertions.assertEquals(3, allProduct.size());
        Assertions.assertEquals("firstBook", allProduct.get(0).getDescription());
        Assertions.assertEquals("secondBook", allProduct.get(1).getDescription());
        Assertions.assertEquals("thirdBook", allProduct.get(2).getDescription());
    }

    @Test
    void getAllProductWithWarehouseInfo() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductByIdWithWarehouseInfo() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}