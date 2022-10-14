package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOID;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOStock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperForAuthorTest {
    private MapperForAuthor mapperForAuthor = new MapperForAuthor();

    @Test
    void authorReqDTOToAuthor() {
        AuthorReqDTO authorReqDTOTest = new AuthorReqDTO("Alexander", "Milne", 1882);
        Author authorTest = mapperForAuthor.authorReqDTOToAuthor(authorReqDTOTest);
        Assertions.assertEquals(authorTest.getFirstName(), authorReqDTOTest.getFirstName());
        Assertions.assertEquals(authorTest.getLastName(), authorReqDTOTest.getLastName());
        Assertions.assertEquals(authorTest.getDateOfBirth(), authorReqDTOTest.getDateOfBirth());
        Assertions.assertNotNull(authorTest);
    }

    @Test
    void authorToRespDTOStock() {
        Author authorTest = new Author("Alexander", "Milne", 1882);
        AuthorRespDTOStock authorRespDTOStock = mapperForAuthor.authorToRespDTOStock(authorTest);
        Assertions.assertNotNull(authorRespDTOStock);
        Assertions.assertEquals(authorTest.getFirstName(), authorRespDTOStock.getFirstName());
        Assertions.assertEquals(authorTest.getLastName(), authorRespDTOStock.getLastName());
        Assertions.assertEquals(authorTest.getDateOfBirth(), authorRespDTOStock.getDateOfBirth());
        Assertions.assertEquals(authorTest.getID(),authorRespDTOStock.getID());

    }

    @Test
    void authorRespDTOID() {
        Author authorTest = new Author("Alexander", "Milne", 1882);
        AuthorRespDTOID authorRespDTOID = mapperForAuthor.authorRespDTOID(authorTest);
        Assertions.assertEquals(authorRespDTOID.getID(),authorTest.getID());
    }
}