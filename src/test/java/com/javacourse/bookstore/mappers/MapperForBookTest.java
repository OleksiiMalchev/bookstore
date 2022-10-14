package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MapperForBookTest {
    private final MapperForAuthor mapperForAuthor = new MapperForAuthor();
    private final MapperForBook mapperForBook = new MapperForBook(mapperForAuthor);



    @Test
    void toBookRespDTO() {
        Book bookTest = new Book("",10L,"","",20,10L,10L,10,50);
        BookRespDTO bookRespDTO = mapperForBook.toBookRespDTO(bookTest);
        Assertions.assertEquals(bookTest.getBarCode(),bookRespDTO.getBarCode());
        Assertions.assertEquals(bookTest.getPages(),bookRespDTO.getPages());
        Assertions.assertEquals(bookTest.getTitle(),bookRespDTO.getTitle());

    }

    @Test
    void getBook() {
    }
}