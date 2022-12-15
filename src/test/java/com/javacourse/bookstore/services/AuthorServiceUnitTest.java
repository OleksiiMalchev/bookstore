package com.javacourse.bookstore.services;

import com.javacourse.bookstore.configuration.TestLightConfig;
import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.repositories.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


    @Test
    void createAuthor() {
        final String testAuthorFirstName = "Steven";
        final String testAuthorLastName = "Kipling";

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
}