package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.domain.Author;
import com.javacourse.bookstore.mappers.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import com.javacourse.bookstore.mappers.MapperAuthorToRespDTO;
import com.javacourse.bookstore.mappers.MapperForAuthor;
import com.javacourse.bookstore.repositories.AuthorRepository;
import exception.AuthorNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorServiceImplTest {
    @Autowired
    private AuthorServiceImpl authorServiceImpl;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private MapperForAuthor mapperForAuthor;
    @MockBean
    private MapperAuthorToRespDTO mapperAuthorToRespDTO;


    @Test
    void getAllAuthor() throws SQLException {
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne").id(555L)
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Author authorDan = Author.builder().firstName("Dan").lastName("Brown").id(444L)
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Author authorJoanne = Author.builder().firstName("Joanne").lastName("Rowling").id(333L)
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        AuthorRespDTO authorAlexanderRespDTO = AuthorRespDTO.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        AuthorRespDTO authorDanRespDTO = AuthorRespDTO.builder().firstName("Dan").lastName("Brown")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        AuthorRespDTO authorJoanneRespDTO = AuthorRespDTO.builder().firstName("Joanne").lastName("Rowling")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Mockito.when(authorRepository.findAll())
                .thenReturn(List.of(authorAlexander, authorDan, authorJoanne));
        Mockito.when(mapperForAuthor.authorToRespDTOStock(authorAlexander))
                .thenReturn(authorAlexanderRespDTO);
        Mockito.when(mapperForAuthor.authorToRespDTOStock(authorDan))
                .thenReturn(authorDanRespDTO);
        Mockito.when(mapperForAuthor.authorToRespDTOStock(authorJoanne))
                .thenReturn(authorJoanneRespDTO);
        List<AuthorRespDTO> allAuthor = authorServiceImpl.getAllAuthor();
        Assertions.assertEquals(allAuthor.size(), 3);
        Assertions.assertEquals(allAuthor.get(0).getFirstName(), authorAlexanderRespDTO.getFirstName());
        Assertions.assertEquals(allAuthor.get(1).getFirstName(), authorDanRespDTO.getFirstName());
        Assertions.assertEquals(allAuthor.get(2).getFirstName(), authorJoanneRespDTO.getFirstName());
        Assertions.assertEquals(allAuthor.get(2).getDateOfBirth(), authorJoanneRespDTO.getDateOfBirth());
    }

    @Test
    void getAuthorByID() throws AuthorNotFoundException {
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne").id(555L)
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        AuthorRespDTO authorAlexanderRespDTO = AuthorRespDTO.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Mockito.when(authorRepository.findById(authorAlexander.getId()))
                .thenReturn(Optional.of(authorAlexander));
        Mockito.when(mapperForAuthor.authorToRespDTOStock(authorAlexander))
                .thenReturn(authorAlexanderRespDTO);
        AuthorRespDTO authorByID = authorServiceImpl.getAuthorById(555L).get();
        Assertions.assertNotNull(authorByID);
        Assertions.assertEquals(authorByID.getFirstName(), authorAlexander.getFirstName());
        Assertions.assertEquals(authorByID.getLastName(), authorAlexander.getLastName());
        Assertions.assertEquals(authorByID.getDateOfBirth(), authorAlexander.getDateOfBirth());
    }

    @Test
    void createAuthor() {
        AuthorReqDTO authorReqDTO = AuthorReqDTO.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        AuthorRespDTO authorRespDTO = AuthorRespDTO.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Mockito.when(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
                .thenReturn(Optional.ofNullable(authorAlexander));
        Mockito.when(authorRepository.save(authorAlexander))
                .thenReturn(authorAlexander);
        Mockito.when(mapperForAuthor.authorToRespDTOStock(authorAlexander))
                .thenReturn(authorRespDTO);
        AuthorRespDTO newAuthorRespDTO = authorServiceImpl.createAuthor(authorReqDTO).get();
        Assertions.assertNotNull(newAuthorRespDTO);
        Assertions.assertEquals(newAuthorRespDTO.getFirstName(), authorReqDTO.getFirstName());
        Assertions.assertEquals(newAuthorRespDTO.getDateOfBirth(), authorReqDTO.getDateOfBirth());
    }

    @Test
    void updateAuthor() {
        AuthorReqDTO authorReqDTO = AuthorReqDTO.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne").id(555L)
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        AuthorRespDTO authorRespDTO = AuthorRespDTO.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Mockito.when(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
                .thenReturn(Optional.ofNullable(authorAlexander));
        Mockito.when(authorRepository.findById(555L))
                .thenReturn(Optional.ofNullable(authorAlexander));
        Mockito.when(mapperForAuthor.authorToRespDTOStock(authorAlexander))
                .thenReturn(authorRespDTO);
        AuthorRespDTO newAuthorRespDTO = authorServiceImpl.updateAuthor(555L, authorReqDTO).get();
        Assertions.assertNotNull(newAuthorRespDTO);
        Assertions.assertEquals(newAuthorRespDTO.getFirstName(), authorReqDTO.getFirstName());
        Assertions.assertEquals(newAuthorRespDTO.getDateOfBirth(), authorReqDTO.getDateOfBirth());
    }
//    @Test
//    void updateAuthorWhereReqNull() {
//
//        Mockito.when(mapperForAuthor.authorReqDTOToAuthor(null))
//                .thenReturn(Optional.empty());
//        Mockito.when(authorRepository.findById(555L))
//                .thenReturn(Optional.empty());
//        Mockito.when(mapperForAuthor.authorToRespDTOStock(null))
//                .thenReturn(null);
//        AuthorRespDTO authorRespDTO = authorServiceImpl.updateAuthor(555L, null).get();
//        Assertions.assertNull(authorRespDTO);
//    }

    @Test
    void deleteAuthor() {
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne").id(555L)
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        AuthorRespDTO authorRespDTO = AuthorRespDTO.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Mockito.when(authorRepository.findById(555L))
                .thenReturn(Optional.ofNullable(authorAlexander));
        Mockito.when(mapperForAuthor.authorToRespDTOStock(authorAlexander))
                .thenReturn(authorRespDTO);
        AuthorRespDTO deleteAuthorRespDTO = authorServiceImpl.deleteAuthor(555L).get();
        Assertions.assertNotNull(deleteAuthorRespDTO);
        Assertions.assertEquals(deleteAuthorRespDTO.getFirstName(), authorAlexander.getFirstName());
        Assertions.assertEquals(deleteAuthorRespDTO.getDateOfBirth(), authorAlexander.getDateOfBirth());
    }

    @Test
    void findAuthorByBook() {
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne").id(555L)
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        AuthorRespDTO authorRespDTO = AuthorRespDTO.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Mockito.when(authorRepository.findAuthorByBook(444L))
                .thenReturn(Optional.ofNullable(authorAlexander));
        Mockito.when(mapperForAuthor.authorToRespDTOStock(authorAlexander))
                .thenReturn(authorRespDTO);
        AuthorRespDTO findAuthorByBookRespDTO = authorServiceImpl.findAuthorByBook(444L).get();
        Assertions.assertNotNull(findAuthorByBookRespDTO);
        Assertions.assertEquals(findAuthorByBookRespDTO.getFirstName(), authorAlexander.getFirstName());
        Assertions.assertEquals(findAuthorByBookRespDTO.getDateOfBirth(), authorAlexander.getDateOfBirth());
    }

    @Test
    void getAuthorWithDetails() {
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne").id(555L)
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        BookRespDTO bookRespDTO = BookRespDTO.builder().title("Book").cover("soft").publishingHouse("Home")
                .pages(500).build();
        BookRespDTO bookRespDTO1 = BookRespDTO.builder().title("Book").cover("soft").publishingHouse("Home")
                .pages(550).build();
        BookRespDTO bookRespDTO2 = BookRespDTO.builder().title("Book").cover("hard").publishingHouse("Home")
                .pages(600).build();
        AuthorRespDTOWithBooks authorRespDTOWithBooks = AuthorRespDTOWithBooks .builder().firstName("Alexander")
                .lastName("Milne").dateOfBirth(LocalDate.of(1881, 5, 20))
                .books(List.of(bookRespDTO,bookRespDTO1,bookRespDTO2)).build();
        Mockito.when(authorRepository.findById(555L))
                .thenReturn(Optional.ofNullable(authorAlexander));
        Mockito.when(mapperAuthorToRespDTO.authorToRespDTO(authorAlexander))
                .thenReturn(authorRespDTOWithBooks);
        AuthorRespDTOWithBooks findAuthorByBookRespDTO = authorServiceImpl.getAuthorWithDetails(555L).get();
        Assertions.assertNotNull(findAuthorByBookRespDTO);
        Assertions.assertEquals(findAuthorByBookRespDTO.getFirstName(), authorAlexander.getFirstName());
        Assertions.assertEquals(findAuthorByBookRespDTO.getDateOfBirth(), authorAlexander.getDateOfBirth());
        Assertions.assertEquals(findAuthorByBookRespDTO.getBooks().get(0).getTitle(),bookRespDTO.getTitle());
        Assertions.assertEquals(findAuthorByBookRespDTO.getBooks().get(1).getPages(),bookRespDTO1.getPages());
        Assertions.assertEquals(findAuthorByBookRespDTO.getBooks().get(2).getCover(),bookRespDTO2.getCover());
    }
}