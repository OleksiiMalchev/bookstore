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
public class BookServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestDbHelper testDbHelper;

    @BeforeEach
    public void beforeEach() {
        testDbHelper.uploadScript("scripts/author_test_script.sql");
        testDbHelper.uploadScript("scripts/book_test_script.sql");
    }
    @AfterEach
    public void afterEach() {
        testDbHelper.cleanTable("book");
        testDbHelper.cleanTable("author");

    }

    @Test
    public void getBookByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", 20055))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId")
                        .value(20055))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title")
                        .value("The Stand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cover")
                        .value("soft"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publishingHouse")
                        .value("Penguin Books"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.barCode")
                        .value(456))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pages")
                        .value(500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn")
                        .value(456))
                .andExpect(MockMvcResultMatchers.jsonPath("$.yearOfPublication")
                        .value("2021-05-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author.firstName")
                        .value("Stephen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author.lastName")
                        .value("King"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.author")
//                        .value("{\"authorId\":10055,\"firstName\":\"Stephen\",\"lastName\":\"King\"}"));

    }

    @Test
    public void findAllBooksByAuthorIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/booksAuthor/{authorId}", 10055))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.content().json("[{\"bookId\":20055,\"title\"" +
                        ":\"The Stand\",\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\",\"barCode\":456," +
                        "\"pages\":500,\"isbn\":456,\"yearOfPublication\":\"2021-05-20\",\"author\":{\"authorId\":10055," +
                        "\"firstName\":\"Stephen\",\"lastName\":\"King\"}}]"));
    }

    @Test
    public void allBooksTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.content().json("[{\"bookId\":20055,\"title\":\"The Stand\"," +
                        "\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\",\"barCode\":456,\"pages\":500,\"isbn\":456," +
                        "\"yearOfPublication\":\"2021-05-20\",\"author\":{\"authorId\":10055,\"firstName\":\"Stephen\"," +
                        "\"lastName\":\"King\"}},{\"bookId\":20056,\"title\":\"Billy Summers\",\"cover\":\"soft\"," +
                        "\"publishingHouse\":\"Penguin Books\",\"barCode\":789,\"pages\":200,\"isbn\":789," +
                        "\"yearOfPublication\":null,\"author\":{\"authorId\":10056,\"firstName\":\"George R.R.\"," +
                        "\"lastName\":\"Martin\"}},{\"bookId\":20057,\"title\":\"A Game of Thrones\"," +
                        "\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\",\"barCode\":123," +
                        "\"pages\":600,\"isbn\":123,\"yearOfPublication\":null,\"author\":{\"authorId\":10056," +
                        "\"firstName\":\"George R.R.\",\"lastName\":\"Martin\"}},{\"bookId\":20058," +
                        "\"title\":\"The Hobbit\",\"cover\":\"soft\",\"publishingHouse\":\"Machaon\"," +
                        "\"barCode\":987,\"pages\":400,\"isbn\":987,\"yearOfPublication\":null," +
                        "\"author\":{\"authorId\":10057,\"firstName\":\"J.R.R\",\"lastName\":\"Tolkien\"}}," +
                        "{\"bookId\":20059,\"title\":\"American Gods 10th Anniversary Edition\",\"cover\":\"soft\"," +
                        "\"publishingHouse\":\"Machaon\",\"barCode\":321,\"pages\":450," +
                        "\"isbn\":321,\"yearOfPublication\":null,\"author\":{\"authorId\":10058,\"firstName\":\"Neil\"," +
                        "\"lastName\":\"Gaiman\"}},{\"bookId\":20060,\"title\":\"A Wizard of Earthsea\"," +
                        "\"cover\":\"soft\",\"publishingHouse\":\"Machaon\",\"barCode\":654,\"pages\":500,\"isbn\":654," +
                        "\"yearOfPublication\":null,\"author\":{\"authorId\":10059,\"firstName\":\"Ursula K.\"," +
                        "\"lastName\":\"Le Guin\"}}]"));
    }

    @Test
    public void createBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content("{\"authorId\":10055,\"title\":\"The Stand\"," +
                                "\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\"," +
                                "\"barCode\":456,\"pages\":500,\"isbn\":456," +
                                "\"yearOfPublication\":\"2021-05-20\"}")
                        .contentType(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Stand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cover").value("soft"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publishingHouse").value("Penguin Books"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.barCode").value(456))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pages").value(500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(456))
                .andExpect(MockMvcResultMatchers.jsonPath("$.yearOfPublication").value("2021-05-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author.firstName").value("Stephen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author.lastName").value("King"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").exists());
    }

    @Test
    public void updateBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/books/{id}", 20055)
                        .content("{\"authorId\":10056,\"title\":\"The Stand\"," +
                                "\"cover\":\"soft\",\"publishingHouse\":\"Penguin Books\"," +
                                "\"barCode\":456,\"pages\":500,\"isbn\":456," +
                                "\"yearOfPublication\":\"2021-05-20\"}")
                        .contentType(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Stand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cover").value("soft"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publishingHouse").value("Penguin Books"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.barCode").value(456))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pages").value(500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(456))
                .andExpect(MockMvcResultMatchers.jsonPath("$.yearOfPublication").value("2021-05-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author.firstName").value("George R.R."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author.lastName").value("Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").exists());
    }
    @Test
    public void deleteBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", 20055))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Stand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cover").value("soft"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publishingHouse").value("Penguin Books"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.barCode").value(456))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pages").value(500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(456))
                .andExpect(MockMvcResultMatchers.jsonPath("$.yearOfPublication").value("2021-05-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author.firstName").value("Stephen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author.lastName").value("King"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").exists());
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", 20055))
                .andExpect(MockMvcResultMatchers.status().is(404));

    }
}
