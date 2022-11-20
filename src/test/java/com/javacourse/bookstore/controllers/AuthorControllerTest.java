package com.javacourse.bookstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacourse.bookstore.BookstoreApplication;
import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureMockMvc
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorService authorService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAuthorByID() throws Exception {

        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881,5,20))
                .id(12547L).build();
        Mockito.when(authorService.getAuthorById(authorAlexander.getId()))
                .thenReturn(Optional.ofNullable(AuthorRespDTO
                        .builder()
                        .id(authorAlexander.getId())
                        .firstName(authorAlexander.getFirstName())
                        .lastName(authorAlexander.getLastName())
                        .dateOfBirth(authorAlexander.getDateOfBirth())
                        .build()));
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", authorAlexander.getId()))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value(authorAlexander.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName")
                        .value(authorAlexander.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth")
                        .value(authorAlexander.getDateOfBirth().format(DateTimeFormatter.ISO_DATE)));
    }

    @Test
    void getAuthorByBook() throws Exception {
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881,05,20)).id(125L).build();
        Book saveBook = Book.builder().authorId(authorAlexander.getId()).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20))
                .price(200L).cost(50L).barCode(1124)
                .id(555L).build();
        Mockito.when(authorService.findAuthorByBook(555L))
                .thenReturn(Optional.ofNullable(AuthorRespDTO
                        .builder()
                        .id(authorAlexander.getId())
                        .firstName(authorAlexander.getFirstName())
                        .lastName(authorAlexander.getLastName())
                        .dateOfBirth(authorAlexander.getDateOfBirth())
                        .build()));
        mockMvc.perform(MockMvcRequestBuilders.get("/authorBooks/{idBook}", saveBook.getId()))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value(authorAlexander.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName")
                        .value(authorAlexander.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth")
                        .value(authorAlexander.getDateOfBirth().format(DateTimeFormatter.ISO_DATE)));
    }

    @Test
    void allAuthors() throws Exception {
        Mockito.when(authorService.getAllAuthor())
                .thenReturn(List.of(AuthorRespDTO
                        .builder()
                        .firstName("Vashy")
                        .build()));
        mockMvc.perform(MockMvcRequestBuilders.get("/authors"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Vashy"));
    }

    @Test
    void getAuthorWithDetails() throws Exception {
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881,05,20)).id(125L).build();
        BookRespDTO saveBook = BookRespDTO.builder().title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20))
                .price(200L).barCode(1124)
                .id(555L).build();
        Mockito.when(authorService.getAuthorWithDetails(authorAlexander.getId()))
                .thenReturn(Optional.ofNullable(AuthorRespDTOWithBooks
                        .builder()
                        .id(authorAlexander.getId())
                        .firstName(authorAlexander.getFirstName())
                        .lastName(authorAlexander.getLastName())
                        .dateOfBirth(authorAlexander.getDateOfBirth())
                        .books(List.of(saveBook))
                        .build()));
        mockMvc.perform(MockMvcRequestBuilders.get("/authorWith/{idAuthor}", authorAlexander.getId()))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value(authorAlexander.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName")
                        .value(authorAlexander.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth")
                        .value(authorAlexander.getDateOfBirth().format(DateTimeFormatter.ISO_DATE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0]").exists());

    }

    @Test
    void createAuthor() throws Exception {
        AuthorReqDTO authorReqDTO = AuthorReqDTO.builder().firstName("Alex").lastName("M")
                .dateOfBirth(LocalDate.of(1881,5,20)).countryOfBirth(null).id(135L).build();
        AuthorRespDTO authorRespDTO = AuthorRespDTO.builder()
                .firstName("Alex").lastName("M").dateOfBirth(LocalDate.of(1881,05,20))
                .dateOfDeath(LocalDate.of(1881,5,20)).id(135L).build();
        Mockito
                .when(authorService.createAuthor(Mockito.any(AuthorReqDTO.class))).thenReturn(Optional.ofNullable(authorRespDTO));
        String writeValueAsString = objectMapper.writeValueAsString(authorReqDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/authors").content(writeValueAsString)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(authorRespDTO.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(authorRespDTO.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth")
                        .value(authorRespDTO.getDateOfBirth().format(DateTimeFormatter.ISO_DATE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryOfBirth").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }


    @Test
    void update() throws Exception {
        AuthorReqDTO authorReqDTO = AuthorReqDTO.builder().firstName("Alex").lastName("M")
                .dateOfBirth(LocalDate.of(1881,5,20)).countryOfBirth(null).id(125L).build();

        AuthorRespDTO authorRespDTO = AuthorRespDTO.builder()
                .firstName("Alex").lastName("M").dateOfBirth(LocalDate.of(1881,05,20))
                .dateOfDeath(LocalDate.of(1881,5,20)).id(125L).build();
        Mockito
                .when(authorService.updateAuthor(Mockito.any(),Mockito.any(AuthorReqDTO.class)))
                .thenReturn(Optional.ofNullable(authorRespDTO));

        String writeValueAsString = objectMapper.writeValueAsString(authorReqDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/{id}", authorReqDTO.getId())
                        .content(writeValueAsString).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(authorRespDTO.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(authorRespDTO.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth")
                        .value(authorRespDTO.getDateOfBirth().format(DateTimeFormatter.ISO_DATE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryOfBirth").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void delete() throws Exception {
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.parse("2020-01-08")).id(125L).build();
        Mockito.when(authorService.deleteAuthor(authorAlexander.getId()))
                .thenReturn(Optional.ofNullable(AuthorRespDTO
                        .builder()
                        .id(authorAlexander.getId())
                        .firstName(authorAlexander.getFirstName())
                        .lastName(authorAlexander.getLastName())
                        .dateOfBirth(authorAlexander.getDateOfBirth())
                        .build()));
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/{id}", authorAlexander.getId()))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(authorAlexander.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(authorAlexander.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth")
                        .value(authorAlexander.getDateOfBirth().format(DateTimeFormatter.ISO_DATE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
}