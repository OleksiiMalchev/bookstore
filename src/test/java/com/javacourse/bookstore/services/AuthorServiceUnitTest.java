package com.javacourse.bookstore.services;

import com.javacourse.bookstore.configuration.TestLightConfig;
import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Warehouse;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.WarehouseReqDTO;
import com.javacourse.bookstore.domain.dto.WarehouseRespDTO;
import com.javacourse.bookstore.repositories.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class AuthorServiceUnitTest extends TestLightConfig {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;
    @Captor
    private ArgumentCaptor<Author> authorArgumentCaptor;

    final Long testAuthorId = 10L;
    final String testAuthorFirstName = "Steven";
    final String testAuthorLastName = "Kipling";
    final Long testAuthorIdSecond = 15L;
    final String testAuthorFirstNameSecond = "Aleksandr";
    final String testAuthorLastNameSecond = "Miln";

    @Test
    void getAllAuthor() throws SQLException {
        Author authorTestFirst = Author.builder()
                .firstName(testAuthorFirstName)
                .lastName(testAuthorLastName)
                .build();
        Author authorTestSecond = Author.builder()
                .firstName(testAuthorFirstNameSecond)
                .lastName(testAuthorLastNameSecond)
                .build();

        when(authorRepository.findAll()).thenReturn(List.of(authorTestFirst, authorTestSecond));
        List<AuthorRespDTO> authorAll = authorService.getAllAuthor();
        verify(authorRepository, times(1)).findAll();
        Assertions.assertEquals(authorAll.size(), 2);
        Assertions.assertEquals(testAuthorFirstName, authorAll.get(0).getFirstName());
        Assertions.assertEquals(testAuthorLastName, authorAll.get(0).getLastName());
        Assertions.assertEquals(testAuthorFirstNameSecond, authorAll.get(1).getFirstName());
        Assertions.assertEquals(testAuthorLastNameSecond, authorAll.get(1).getLastName());

    }
    @Test
    void getAuthorById(){
        Author authorToFind = Author.builder()
                .firstName(testAuthorFirstName)
                .lastName(testAuthorLastName)
                .build();

        when(authorRepository.findById(testAuthorId)).thenReturn(Optional.ofNullable(authorToFind));
        Optional<AuthorRespDTO> author = authorService.getAuthorById(testAuthorId);
        verify(authorRepository, times(1)).findById(testAuthorId);
        Assertions.assertTrue(author.isPresent());
        Assertions.assertEquals(testAuthorFirstName, author.get().getFirstName());
        Assertions.assertEquals(testAuthorLastName, author.get().getLastName());
    }

    @Test
    void createAuthor() {
        AuthorReqDTO authorReqDTO = AuthorReqDTO.builder()
                .firstName(testAuthorFirstName)
                .lastName(testAuthorLastName)
                .build();

        Author authorToSave = Author.builder().firstName(testAuthorFirstName).lastName(testAuthorLastName).build();
        when(authorRepository.save(any(Author.class))).thenReturn(authorToSave);
        Optional<AuthorRespDTO> author = authorService.createAuthor(authorReqDTO);
        verify(authorRepository, times(1)).save(authorArgumentCaptor.capture());
        Author authorEntity = authorArgumentCaptor.getValue();

        Assertions.assertEquals(testAuthorFirstName, authorEntity.getFirstName());
        Assertions.assertEquals(testAuthorLastName, authorEntity.getLastName());
        Assertions.assertTrue(author.isPresent());
        Assertions.assertEquals(testAuthorFirstName, author.get().getFirstName());
        Assertions.assertEquals(testAuthorLastName, author.get().getLastName());
    }
    @Test
    void updateAuthor(){
        AuthorReqDTO authorReqDTO = AuthorReqDTO.builder()
                .firstName(testAuthorFirstNameSecond)
                .lastName(testAuthorLastNameSecond)
                .build();
        Author authorOrigin = Author.builder()
                .id(testAuthorId)
                .firstName(testAuthorFirstName)
                .lastName(testAuthorLastName)
                .build();
        Author authorChange = Author.builder()
                .id(testAuthorId)
                .firstName(testAuthorFirstNameSecond)
                .lastName(testAuthorLastNameSecond)
                .build();
        when(authorRepository.existsById(testAuthorId)).thenReturn(true);
        when(authorRepository.findById(testAuthorId)).thenReturn(Optional.ofNullable(authorOrigin));
        when(authorRepository.save(authorChange)).thenReturn(authorChange);
        when(authorRepository.findById(testAuthorId)).thenReturn(Optional.ofNullable(authorChange));
        Optional<AuthorRespDTO> author = authorService.updateAuthor(testAuthorId, authorReqDTO);
        Assertions.assertTrue(author.isPresent());
        Assertions.assertEquals(testAuthorFirstNameSecond, author.get().getFirstName());
        Assertions.assertEquals(testAuthorLastNameSecond, author.get().getLastName());
    }
    @Test
    void deleteAuthor(){
        Author author = Author.builder()
                .id(testAuthorId)
                .firstName(testAuthorFirstName)
                .lastName(testAuthorLastName)
                .build();
        when(authorRepository.existsById(author.getId())).thenReturn(true);
        authorRepository.deleteById(author.getId());
        when(authorRepository.existsById(author.getId())).thenReturn(false);
        Optional<AuthorRespDTO> authorDelete = authorService.deleteAuthor(author.getId());

        Assertions.assertFalse(authorDelete.isPresent());

    }
}