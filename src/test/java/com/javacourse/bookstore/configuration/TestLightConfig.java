package com.javacourse.bookstore.configuration;

import com.javacourse.bookstore.repositories.AuthorRepository;
import com.javacourse.bookstore.repositories.BookRepository;
import com.javacourse.bookstore.repositories.ProductRepository;
import com.javacourse.bookstore.repositories.UserRepository;
import com.javacourse.bookstore.repositories.WarehouseRepository;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Light config for unit tests
 */
@SpringBootTest
@ImportAutoConfiguration(exclude = {FlywayAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
public class TestLightConfig {

    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private WarehouseRepository warehouseRepository;

}
