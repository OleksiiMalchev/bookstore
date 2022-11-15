package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperAuthorToRespDTOTest {
    @Autowired
    private MapperAuthorToRespDTO mapperAuthorToRespDTO;

    @Test
    void authorToRespDTO() {
        Author authorTest = Author.builder().id(125L).firstName("Alexandr").lastName("Mixio").build();
        AuthorRespDTOWithBooks authorRespDTOWithBooks = mapperAuthorToRespDTO.authorToRespDTO(authorTest);
        Assertions.assertNotNull(authorRespDTOWithBooks);
        Assertions.assertEquals(authorTest.getId(), authorRespDTOWithBooks.getId());
        Assertions.assertEquals(authorTest.getFirstName(), authorRespDTOWithBooks.getFirstName());
        Assertions.assertEquals(authorTest.getLastName(), authorRespDTOWithBooks.getLastName());
    }
}
