package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MapperForBookTest {
    private final MapperForAuthor mapperForAuthor = new MapperForAuthor();
    private final MapperForBook mapperForBook = new MapperForBook(mapperForAuthor);


    @Test
    void toBookRespDTO() {
        Book bookTest = new Book("The Day's Play", 124567890L, "soft", "XZ",
                1910, 200L, 50, 1124, 314);
        BookRespDTO bookRespDTO = mapperForBook.toBookRespDTO(bookTest);
        Assertions.assertEquals(bookTest.getBarCode(), bookRespDTO.getBarCode());
        Assertions.assertEquals(bookTest.getPages(), bookRespDTO.getPages());
        Assertions.assertEquals(bookTest.getTitle(), bookRespDTO.getTitle());
        Assertions.assertEquals(bookTest.getID(), bookRespDTO.getID());

    }

    @Test
    void getBook() {
        BookReqDTO bookReqDTOTest = new BookReqDTO("The Day's Play", 124567890L, "soft", "XZ",
                1910, 200L, 50, 314);
        Book bookTest = mapperForBook.getBook(bookReqDTOTest);
        Assertions.assertEquals(bookTest.getAuthorID(), bookReqDTOTest.getAuthorID());
    }
}