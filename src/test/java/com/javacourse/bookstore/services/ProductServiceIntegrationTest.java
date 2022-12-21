package com.javacourse.bookstore.services;

import com.javacourse.bookstore.configuration.TestDatabaseConfig;
import com.javacourse.bookstore.utils.TestDbHelper;
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
public class ProductServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestDbHelper testDbHelper;

    @BeforeEach
    public void beforeEach() {
        testDbHelper.cleanTable("book");
        testDbHelper.cleanTable("author");
        testDbHelper.cleanTable("product");
        testDbHelper.cleanTable("warehouse");
        testDbHelper.uploadScript("scripts/author_test_script.sql");
        testDbHelper.uploadScript("scripts/book_test_script.sql");
        testDbHelper.uploadScript("scripts/product_test_script.sql");
        testDbHelper.uploadScript("scripts/warehouse_test_script.sql");
    }

//    @AfterEach
//    public void afterEach() {
//        testDbHelper.cleanTable("book");
//        testDbHelper.cleanTable("author");
//        testDbHelper.cleanTable("product");
//        testDbHelper.cleanTable("warehouse");
//    }

    @Test
    public void getProductById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 30055))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value(30055))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price")
                        .value("120"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value("Some Book"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.title")
                        .value("The Stand"));
    }

    @Test
    public void getProductByIdWithDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/productWarehouse/{idProduct}", 30055))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(30055))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("120"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Some Book"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.title").value("The Stand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.warehouse.id").value(40055))
                .andExpect(MockMvcResultMatchers.jsonPath("$.warehouse.productId").value(30055))
                .andExpect(MockMvcResultMatchers.jsonPath("$.warehouse.bookQuantity").value(120))
                .andExpect(MockMvcResultMatchers.jsonPath("$.warehouse.reserve").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.warehouse.sale").value(50));

    }

    @Test
    public void getAllProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.content().json("[{\"id\":30055,\"price\":120," +
                        "\"description\":\"Some Book\",\"book\":{\"bookId\":20055,\"title\":\"The Stand\"," +
                        "\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\",\"barCode\":456,\"pages\":500," +
                        "\"isbn\":456,\"yearOfPublication\":\"2021-05-20\",\"author\":{\"authorId\":10055," +
                        "\"firstName\":\"Stephen\",\"lastName\":\"King\"}}},{\"id\":30056,\"price\":180," +
                        "\"description\":\"Some Book\",\"book\":{\"bookId\":20056,\"title\":\"Billy Summers\"," +
                        "\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\",\"barCode\":789,\"pages\":200," +
                        "\"isbn\":789,\"yearOfPublication\":null,\"author\":{\"authorId\":10056," +
                        "\"firstName\":\"George R.R.\",\"lastName\":\"Martin\"}}},{\"id\":30057," +
                        "\"price\":240,\"description\":\"Some Book\",\"book\":{\"bookId\":20057," +
                        "\"title\":\"A Game of Thrones\",\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\"," +
                        "\"barCode\":123,\"pages\":600,\"isbn\":123,\"yearOfPublication\":null," +
                        "\"author\":{\"authorId\":10056,\"firstName\":\"George R.R.\"," +
                        "\"lastName\":\"Martin\"}}},{\"id\":30058,\"price\":300,\"description\":\"Some Book\"," +
                        "\"book\":{\"bookId\":20058,\"title\":\"The Hobbit\",\"cover\":\"soft\"," +
                        "\"publishingHouse\":\"Machaon\",\"barCode\":987,\"pages\":400,\"isbn\":987," +
                        "\"yearOfPublication\":null,\"author\":{\"authorId\":10057,\"firstName\":\"J.R.R\"," +
                        "\"lastName\":\"Tolkien\"}}},{\"id\":30059,\"price\":360,\"description\":\"Some Book\"," +
                        "\"book\":{\"bookId\":20059,\"title\":\"American Gods 10th Anniversary Edition\"," +
                        "\"cover\":\"soft\",\"publishingHouse\":\"Machaon\",\"barCode\":321,\"pages\":450," +
                        "\"isbn\":321,\"yearOfPublication\":null,\"author\":{\"authorId\":10058," +
                        "\"firstName\":\"Neil\",\"lastName\":\"Gaiman\"}}},{\"id\":30060," +
                        "\"price\":420,\"description\":\"Some Book\",\"book\":{\"bookId\":20060," +
                        "\"title\":\"A Wizard of Earthsea\",\"cover\":\"soft\"," +
                        "\"publishingHouse\":\"Machaon\",\"barCode\":654,\"pages\":500,\"isbn\":654," +
                        "\"yearOfPublication\":null,\"author\":{\"authorId\":10059,\"firstName\":\"Ursula K.\"," +
                        "\"lastName\":\"Le Guin\"}}}]"));
    }
    @Test
    public void getAllProductWithWarehouseInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/info"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.content().json("[{\"id\":30055,\"price\":120," +
                        "\"description\":\"Some Book\",\"book\":{\"bookId\":20055,\"title\":\"The Stand\"," +
                        "\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\",\"barCode\":456,\"pages\":500," +
                        "\"isbn\":456,\"yearOfPublication\":\"2021-05-20\",\"author\":{\"authorId\":10055," +
                        "\"firstName\":\"Stephen\",\"lastName\":\"King\"}},\"warehouse\":{\"id\":40055," +
                        "\"productId\":30055,\"bookQuantity\":120,\"reserve\":null,\"sale\":null}}," +
                        "{\"id\":30056,\"price\":180,\"description\":\"Some Book\",\"book\":{\"bookId\":20056," +
                        "\"title\":\"Billy Summers\",\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\"," +
                        "\"barCode\":789,\"pages\":200,\"isbn\":789,\"yearOfPublication\":null," +
                        "\"author\":{\"authorId\":10056,\"firstName\":\"George R.R.\",\"lastName\":\"Martin\"}}," +
                        "\"warehouse\":{\"id\":40056,\"productId\":30056,\"bookQuantity\":120,\"reserve\":null," +
                        "\"sale\":null}},{\"id\":30057,\"price\":240,\"description\":\"Some Book\"," +
                        "\"book\":{\"bookId\":20057,\"title\":\"A Game of Thrones\",\"cover\":\"soft\"," +
                        "\"publishingHouse\":\"Penguin Books\",\"barCode\":123,\"pages\":600,\"isbn\":123," +
                        "\"yearOfPublication\":null,\"author\":{\"authorId\":10056,\"firstName\":\"George R.R.\"," +
                        "\"lastName\":\"Martin\"}},\"warehouse\":{\"id\":40057,\"productId\":30057," +
                        "\"bookQuantity\":120,\"reserve\":null,\"sale\":null}},{\"id\":30058,\"price\":300," +
                        "\"description\":\"Some Book\",\"book\":{\"bookId\":20058,\"title\":\"The Hobbit\"," +
                        "\"cover\":\"soft\",\"publishingHouse\":\"Machaon\",\"barCode\":987,\"pages\":400," +
                        "\"isbn\":987,\"yearOfPublication\":null,\"author\":{\"authorId\":10057," +
                        "\"firstName\":\"J.R.R\",\"lastName\":\"Tolkien\"}},\"warehouse\":{\"id\":40058," +
                        "\"productId\":30058,\"bookQuantity\":120,\"reserve\":null,\"sale\":null}}," +
                        "{\"id\":30059,\"price\":360,\"description\":\"Some Book\",\"book\":{\"bookId\":20059," +
                        "\"title\":\"American Gods 10th Anniversary Edition\",\"cover\":\"soft\"," +
                        "\"publishingHouse\":\"Machaon\",\"barCode\":321,\"pages\":450,\"isbn\":321," +
                        "\"yearOfPublication\":null,\"author\":{\"authorId\":10058,\"firstName\":\"Neil\"," +
                        "\"lastName\":\"Gaiman\"}},\"warehouse\":{\"id\":40059,\"productId\":30059," +
                        "\"bookQuantity\":120,\"reserve\":null,\"sale\":null}},{\"id\":30060,\"price\":420," +
                        "\"description\":\"Some Book\",\"book\":{\"bookId\":20060,\"title\":\"A Wizard of Earthsea\"," +
                        "\"cover\":\"soft\",\"publishingHouse\":\"Machaon\",\"barCode\":654,\"pages\":500," +
                        "\"isbn\":654,\"yearOfPublication\":null,\"author\":{\"authorId\":10059," +
                        "\"firstName\":\"Ursula K.\",\"lastName\":\"Le Guin\"}}," +
                        "\"warehouse\":{\"id\":40060,\"productId\":30060,\"bookQuantity\":120,\"reserve\":null," +
                        "\"sale\":null}}]"));
    }
    @Test
    public void createProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .content("{\"bookId\":20055,\"initialPrice\":150," +
                                "\"description\":\"Some BOOK\"}")
                        .contentType(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(180))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Some BOOK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.title").value("The Stand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    @Test
    public void updateProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/products/{idProduct}", 30055)
                        .content("{\"bookId\":20055,\"initialPrice\":200," +
                                "\"description\":\"Some BOOK update\"}")
                        .contentType(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(240))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Some BOOK update"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.title").value("The Stand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    @Test
    public void deleteProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{idProduct}", 30055))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(30055))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(120))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Some Book"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.title").value("The Stand"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{idProduct}", 30055))
                .andExpect(MockMvcResultMatchers.status().is(404));

    }

}
