//package com.javacourse.bookstore.mappers;
//
//import com.javacourse.bookstore.mappers.domain.Book;
//import com.javacourse.bookstore.mappers.domain.dto.BookReqDTO;
//import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class MapperForBookTest {
//    @Autowired
//    private MapperForBook mapperForBook;
//    private final Book bookTest= Book.builder()
//            .title("The Day's Play").cover("soft").publishingHouse("XZ")
//            .yearOfPublication(LocalDate.of(1910, 5, 20)).price(200L).cost(50L).barCode(1124)
//            .id(314L).build();
//    private final BookReqDTO bookReqDTOTest = BookReqDTO.builder().title("The Day's Play").cover("soft")
//            .publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20)).cost(50L)
//            .barCode(1124).build();
//
//    @Test
//    void toBookRespDTO() {
//        BookRespDTO bookRespDTO = mapperForBook.toBookRespDTO(bookTest);
//        Assertions.assertEquals(bookTest.getBarCode(), bookRespDTO.getBarCode());
//        Assertions.assertEquals(bookTest.getPages(), bookRespDTO.getPages());
//        Assertions.assertEquals(bookTest.getTitle(), bookRespDTO.getTitle());
//        Assertions.assertEquals(bookTest.getId(), bookRespDTO.getId());
//    }
//    @Test
//    void getBook() {
//        Optional<Book> bookTest = mapperForBook.getBook(bookReqDTOTest);
//        Assertions.assertEquals(bookTest.get().getAuthorId(), bookReqDTOTest.getAuthorId());
//    }
//}