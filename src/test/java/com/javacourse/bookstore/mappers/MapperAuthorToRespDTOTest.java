package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperAuthorToRespDTOTest {
    private MapperForAuthor mapperForAuthor = new MapperForAuthor();
    private MapperForBook mapperForBook = new MapperForBook(mapperForAuthor);
    private MapperAuthorToRespDTO mapperAuthorToRespDTO = new MapperAuthorToRespDTO(mapperForBook);

    @Test
    void authorToRespDTO() {
        Author authorTest= new Author("Alexander", "Milne", 1882);
        AuthorRespDTOWithBooks authorRespDTOWithBooks = mapperAuthorToRespDTO.authorToRespDTO(authorTest);
        Assertions.assertNotNull(authorRespDTOWithBooks);
        Assertions.assertEquals(authorRespDTOWithBooks.getID(),authorTest.getID());
    }
}