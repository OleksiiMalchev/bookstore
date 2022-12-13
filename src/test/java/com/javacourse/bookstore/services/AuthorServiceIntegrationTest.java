package com.javacourse.bookstore.services;

import com.javacourse.bookstore.configuration.TestDatabaseConfig;
import com.javacourse.bookstore.utils.TestDbHelper;
import org.junit.jupiter.api.AfterEach;
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
public class AuthorServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestDbHelper testDbHelper;

    @AfterEach
    public void beforeEach() {
        testDbHelper.cleanTable("author");
    }


    @Test
    public void getAuthorByIdTest() throws Exception {
        testDbHelper.uploadScript("scripts/author_test_script.sql");
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", 10055))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value("Stephen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName")
                        .value("King"));
    }

    @Test
    public void createAuthorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                        .content("{\"firstName\": \"Steven\", \"lastName\": \"Kipling\"}")
                        .contentType(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Steven"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Kipling"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void getAllTest() throws Exception {
        testDbHelper.uploadScript("scripts/author_test_script.sql");
        mockMvc.perform(MockMvcRequestBuilders.get("/authors"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(MockMvcResultMatchers.content().json("[{\"authorId\":10055,\"firstName\":\"Stephen\",\"lastName\":\"King\"}," +
                        "{\"authorId\":10056,\"firstName\":\"George R.R.\",\"lastName\":\"Martin\"}," +
                        "{\"authorId\":10057,\"firstName\":\"J.R.R\",\"lastName\":\"Tolkien\"}," +
                        "{\"authorId\":10058,\"firstName\":\"Neil\",\"lastName\":\"Gaiman\"}," +
                        "{\"authorId\":10059,\"firstName\":\"Ursula K.\",\"lastName\":\"Le Guin\"}]"));
    }

}
