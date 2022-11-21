package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.mappers.domain.Author;
import com.javacourse.bookstore.mappers.domain.Book;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperAuthorToRespDTOTest {
    @Autowired
    private MapperAuthorToRespDTO mapperAuthorToRespDTO;
    @MockBean
    private  MapperForBook mapperForBook;

    @Test
    void authorToRespDTO() {
        Author authorTest = Author.builder().id(125L).firstName("Alexandr").lastName("Mixio").build();
        Book firstBookInList = Book.builder().authorId(125L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .price(200L).cost(50L).barCode(1124).id(314L).build();
        BookRespDTO firstBookRespDTO = BookRespDTO.builder().title("The Day's Play")
                .cover("soft").publishingHouse("XZ")
                .yearOfPublication(LocalDate.of(1912, 5, 20)).build();
        Mockito.when(mapperForBook.toBookRespDTO(firstBookInList))
                .thenReturn(firstBookRespDTO);
        AuthorRespDTOWithBooks authorRespDTOWithBooks = mapperAuthorToRespDTO.authorToRespDTO(authorTest);
        Assertions.assertNotNull(authorRespDTOWithBooks);
        Assertions.assertEquals(authorTest.getId(), authorRespDTOWithBooks.getId());
        Assertions.assertEquals(authorTest.getFirstName(), authorRespDTOWithBooks.getFirstName());
        Assertions.assertEquals(authorTest.getLastName(), authorRespDTOWithBooks.getLastName());
    }
}
