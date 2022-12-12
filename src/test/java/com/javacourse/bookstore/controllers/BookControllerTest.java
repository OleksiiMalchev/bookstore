//package com.javacourse.bookstore.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.javacourse.bookstore.BookstoreApplication;
//import com.javacourse.bookstore.mappers.domain.Book;
//import com.javacourse.bookstore.mappers.domain.dto.BookReqDTO;
//import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
//import com.javacourse.bookstore.services.BookService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(
//        classes = BookstoreApplication.class)
//@AutoConfigureMockMvc
//class BookControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private BookService bookService;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void getBookByID() throws Exception {
//        Book saveBook = Book.builder().authorId(1235L).title("The Day's Play")
//                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20))
//                .id(555L).build();
//        Mockito.when(bookService.getBookById(saveBook.getId()))
//                .thenReturn(Optional.ofNullable(BookRespDTO
//                        .builder()
//                        .id(saveBook.getId())
//                        .title(saveBook.getTitle())
//                        .cover(saveBook.getCover())
//                        .publishingHouse(saveBook.getPublishingHouse())
//                        .build()));
//        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", saveBook.getId()))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title")
//                        .value(saveBook.getTitle()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.cover")
//                        .value(saveBook.getCover()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.publishingHouse")
//                        .value(saveBook.getPublishingHouse()));
//    }
//
//    @Test
//    void findAllByAuthorID() throws Exception {
//        Book saveBook = Book.builder().authorId(1235L).title("The Day's Play")
//                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20)).barCode(1124).id(555L).build();
//        Mockito.when(bookService.allBooksAuthor(10L))
//                .thenReturn(List.of(BookRespDTO
//                        .builder()
//                        .id(saveBook.getId())
//                        .title(saveBook.getTitle())
//                        .cover(saveBook.getCover())
//                        .publishingHouse(saveBook.getPublishingHouse())
//                        .build()));
//        mockMvc.perform(MockMvcRequestBuilders.get("/booksAuthor/{authorID}", 10L))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Day's Play"));
//    }
//
//    @Test
//    void allBooks() throws Exception {
//        Book saveBook = Book.builder().authorId(1235L).title("The Day's Play")
//                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
//                .barCode(1124).id(555L).build();
//        Mockito.when(bookService.allBooks())
//                .thenReturn(List.of(BookRespDTO
//                        .builder()
//                        .id(saveBook.getId())
//                        .title(saveBook.getTitle())
//                        .cover(saveBook.getCover())
//                        .publishingHouse(saveBook.getPublishingHouse())
//                        .build()));
//        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Day's Play"));
//    }
//
//    @Test
//    void create() throws Exception {
//        BookReqDTO bookReqDTO = BookReqDTO.builder().title("The Day's Play")
//                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
//                .cost(50L).barCode(1124).build();
//        BookRespDTO bookRespDTO = BookRespDTO.builder().title("The Day's Play")
//                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
//                .price(200L).barCode(1124).id(314L).build();
//        Mockito
//                .when(bookService.create(Mockito.any(BookReqDTO.class))).thenReturn(Optional.ofNullable(bookRespDTO));
//        String writeValueAsString = objectMapper.writeValueAsString(bookReqDTO);
//        mockMvc.perform(MockMvcRequestBuilders.post("/books").content(writeValueAsString )
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().is(201))
//               .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.cover").value(bookRespDTO.getCover()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.publishingHouse").value(bookRespDTO.getPublishingHouse()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.barCode").value(bookRespDTO.getBarCode()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.pages").value(bookRespDTO.getPages()));
//    }
//
//    @Test
//    void update() throws Exception {
//        BookReqDTO bookReqDTO = BookReqDTO.builder().title("The Day's Play")
//                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20))
//                .cost(50L).barCode(1124).id(125L)
//                .build();
//        BookRespDTO bookRespDTO = BookRespDTO.builder().title("The Day's Play")
//                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
//                .price(200L).barCode(1124).id(125L).build();
//        Mockito
//                .when(bookService.update(Mockito.any(),Mockito.any(BookReqDTO.class)))
//                .thenReturn(Optional.ofNullable(bookRespDTO));
//
//        String writeValueAsString = objectMapper.writeValueAsString(bookReqDTO);
//        mockMvc.perform(MockMvcRequestBuilders.put("/books/{id}", bookReqDTO.getId())
//                        .content(writeValueAsString).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.cover").value(bookRespDTO.getCover()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.publishingHouse")
//                        .value(bookRespDTO.getPublishingHouse()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.barCode").value(bookRespDTO.getBarCode()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.pages").value(bookRespDTO.getPages()));
//
//    }
//
//    @Test
//    void delete() throws Exception {
//
//        Book bookInBase = Book.builder().authorId(555L).title("The Day's Play")
//                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20))
//                .barCode(1124).id(314L).build();
//        BookRespDTO bookRespDTO = BookRespDTO.builder().title("The Day's Play")
//                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20))
//                .price(200L).barCode(1124)
//                .id(314L).build();
//        Mockito.when(bookService.delete(bookInBase.getId())).thenReturn(Optional.ofNullable(bookRespDTO));
//        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", bookInBase.getId()))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.cover").value(bookRespDTO.getCover()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.publishingHouse")
//                        .value(bookRespDTO.getPublishingHouse()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.barCode").value(bookRespDTO.getBarCode()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.pages").value(bookRespDTO.getPages()));
//    }
//}