package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
class MapperForAuthorTest {
    @Autowired
    private MapperForAuthor mapperForAuthor;

    private final AuthorReqDTO authorReqDTO = AuthorReqDTO.builder().firstName("Alexander").lastName("Milne")
            .dateOfBirth(LocalDate.of(1881,05,20)).build();
    private final Author author = Author.builder().firstName("Alexander").lastName("Milne")
            .dateOfBirth(LocalDate.of(1881,05,20)).build();



    @Test
    void authorReqDTOToAuthor() {
        Author authorTest = mapperForAuthor.authorReqDTOToAuthor(authorReqDTO);
        Assertions.assertEquals(authorTest.getFirstName(), authorReqDTO.getFirstName());
        Assertions.assertEquals(authorTest.getLastName(), authorReqDTO.getLastName());
        Assertions.assertEquals(authorTest.getDateOfBirth(), authorReqDTO.getDateOfBirth());
        Assertions.assertNotNull(authorTest);
    }

    @Test
    void authorToRespDTOStock() {
        AuthorRespDTO authorRespDTO = mapperForAuthor.authorToRespDTOStock(author);
        Assertions.assertNotNull(authorRespDTO);
        Assertions.assertEquals(author.getFirstName(), authorRespDTO.getFirstName());
        Assertions.assertEquals(author.getLastName(), authorRespDTO.getLastName());
        Assertions.assertEquals(author.getDateOfBirth(), authorRespDTO.getDateOfBirth());
        Assertions.assertEquals(author.getID(), authorRespDTO.getId());
    }

    @Test
    void authorRespDTOID() {
        AuthorRespDTOID authorRespDTOID = mapperForAuthor.authorRespDTOID(author);
        Assertions.assertEquals(authorRespDTOID.getId(), author.getID());
    }

}