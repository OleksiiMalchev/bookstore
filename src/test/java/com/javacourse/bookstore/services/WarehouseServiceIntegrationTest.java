package com.javacourse.bookstore.services;

import com.javacourse.bookstore.configuration.TestDatabaseConfig;
import com.javacourse.bookstore.utils.TestDbHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestDatabaseConfig.class)
public class WarehouseServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestDbHelper testDbHelper;

    @BeforeEach
    public void beforeEach() {
        testDbHelper.cleanTable("warehouse");
        testDbHelper.cleanTable("product");
        testDbHelper.cleanTable("book");
        testDbHelper.cleanTable("author");
    }

    @Test
    public void getWarehouseByIdTest() throws Exception {
        testDbHelper.uploadScript("scripts/author_test_script.sql");
        testDbHelper.uploadScript("scripts/book_test_script.sql");
        testDbHelper.uploadScript("scripts/product_test_script.sql");
        testDbHelper.uploadScript("scripts/warehouse_test_script.sql");
        mockMvc.perform(MockMvcRequestBuilders.get("/warehouses/{id}", 100))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookQuantity")
                        .value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId")
                        .value(1000));
    }

//    public void createWarehouseTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/warehouses"))
//                .content("{\"productId\": 1000, \"bookQuantity\": \"Kipling\"}")
//                .contentType(MediaType.APPLICATION_JSON.getMediaType()))
//
//    }
}
