package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.domain.Author;
import com.javacourse.bookstore.mappers.domain.Book;
import com.javacourse.bookstore.mappers.domain.dto.BookReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import com.javacourse.bookstore.mappers.MapperForBook;
import com.javacourse.bookstore.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private BookServiceImpl bookService;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private MapperForBook mapperForBook;
//    @MockBean
//    private AuthorRepositories authorRepositories;


    @Test
    void allBooks() {
        Book firstBookInList = Book.builder().authorId(555L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20))
                .price(200L).cost(50L).barCode(1124).id(314L).build();
        Book secondBookInList = Book.builder().authorId(5555L).title("The Holiday Round")
                .cover("hard").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .price(300L).cost(50L).barCode(5247).id(314L).build();
        Book thirdBookInList = Book.builder().authorId(55555L).title("Once a Week")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1914, 5, 20))
                .price(350L).cost(50L).barCode(5247)
                .id(255L).build();
        BookRespDTO firstBookRespDTO = BookRespDTO.builder().title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20))
                .price(200L).barCode(1124).id(314L).build();
        BookRespDTO secondBookRespDTO = BookRespDTO.builder().title("The Holiday Round")
                .cover("hard").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .price(300L).barCode(5247).id(314L).build();
        BookRespDTO thirdBookRespDTO = BookRespDTO.builder().title("Once a Week")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1914, 5, 20))
                .price(350L).barCode(5247).id(255L).build();
        Mockito.when(bookRepository.findAll())
                .thenReturn(List.of(firstBookInList, secondBookInList, thirdBookInList));
        Mockito.when(mapperForBook.toBookRespDTO(firstBookInList)).thenReturn(firstBookRespDTO);
        Mockito.when(mapperForBook.toBookRespDTO(secondBookInList)).thenReturn(secondBookRespDTO);
        Mockito.when(mapperForBook.toBookRespDTO(thirdBookInList)).thenReturn(thirdBookRespDTO);
        List<BookRespDTO> listOfBookRespDTO = bookService.allBooks();
        Assertions.assertEquals(listOfBookRespDTO.size(), 3);
        Assertions.assertEquals(listOfBookRespDTO.get(0).getTitle(), firstBookInList.getTitle());
        Assertions.assertEquals(listOfBookRespDTO.get(1).getTitle(), secondBookInList.getTitle());
        Assertions.assertEquals(listOfBookRespDTO.get(2).getTitle(), thirdBookInList.getTitle());
    }

    @Test
    void allBooksAuthor() {
        Book firstBookInList = Book.builder().authorId(555L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .price(200L).cost(50L).barCode(1124)
                .id(314L).build();
        Book secondBookInList = Book.builder().authorId(555L).title("The Holiday Round")
                .cover("hard").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .price(300L).cost(50L).barCode(5247)
                .id(314L).build();
        Book thirdBookInList = Book.builder().authorId(5555L).title("Once a Week")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1914, 5, 20))
                .price(350L).cost(50L).barCode(5247)
                .id(255L).build();
        BookRespDTO firstBookRespDTO = BookRespDTO.builder().title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1910, 5, 20))
                .price(200L).barCode(1124)
                .id(314L).build();
        BookRespDTO secondBookRespDTO = BookRespDTO.builder().title("The Holiday Round")
                .cover("hard").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .price(300L).barCode(5247)
                .id(314L).build();
        BookRespDTO thirdBookRespDTO = BookRespDTO.builder().title("Once a Week")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1914, 5, 20))
                .price(350L).barCode(5247)
                .id(255L).build();
        Mockito.when(bookRepository.findAllByAuthorID(555L))
                .thenReturn(List.of(firstBookInList, secondBookInList));
        Mockito.when(mapperForBook.toBookRespDTO(firstBookInList)).thenReturn(firstBookRespDTO);
        Mockito.when(mapperForBook.toBookRespDTO(secondBookInList)).thenReturn(secondBookRespDTO);
        Mockito.when(mapperForBook.toBookRespDTO(thirdBookInList)).thenReturn(thirdBookRespDTO);
        List<BookRespDTO> listOfBookRespDTO = bookService.allBooksAuthor(555L);
        Assertions.assertEquals(listOfBookRespDTO.size(), 2);
        Assertions.assertNotEquals(listOfBookRespDTO.size(), 5);
        Assertions.assertEquals(listOfBookRespDTO.get(0).getTitle(), firstBookInList.getTitle());
        Assertions.assertEquals(listOfBookRespDTO.get(1).getTitle(), secondBookInList.getTitle());
    }

    @Test
    void getBookById() {
        Book firstBookInList = Book.builder().authorId(555L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .price(200L).cost(50L).barCode(1124).id(314L).build();
        BookRespDTO firstBookRespDTO = BookRespDTO.builder().title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .price(200L).barCode(1124).id(314L).build();
        Mockito.when(bookRepository.findById(314L))
                .thenReturn(Optional.ofNullable(firstBookInList));
        Mockito.when(mapperForBook.toBookRespDTO(firstBookInList))
                .thenReturn(firstBookRespDTO);
        Optional<BookRespDTO> bookById = bookService.getBookById(314L);
        Assertions.assertEquals(firstBookInList.getPages(), bookById.get().getPages());
        Assertions.assertEquals(firstBookInList.getTitle(), bookById.get().getTitle());

    }

    @Test
    void create() {
        BookReqDTO bookReqDTO = BookReqDTO.builder().title("Book").authorId(125L).cover("soft").publishingHouse("Home")
                .pages(500).build();
        Book book;
        Mockito.when(mapperForBook.getBook(bookReqDTO)).thenReturn(Optional.ofNullable(book = Book.builder()
                .title("Book").authorId(125L).cover("soft").publishingHouse("Home").pages(500).build()));
        Book saveBook;
        Mockito.when(bookRepository.save(book)).thenReturn(saveBook = Book.builder().title("Book").authorId(125L)
                .cover("soft").publishingHouse("Home").pages(500).build());
        Mockito.when(mapperForBook.toBookRespDTO(saveBook)).thenReturn(BookRespDTO.builder().title("Book")
                .cover("soft").publishingHouse("Home").pages(500).build());
        Optional<BookRespDTO> bookRespDTO = bookService.create(bookReqDTO);
        Assertions.assertNotNull(bookRespDTO);
        Assertions.assertEquals(bookReqDTO.getTitle(), bookRespDTO.get().getTitle());
    }

    @Test
    void update() {
        Author authorByIdFromBookInBase = Author.builder().firstName("Dan").lastName("Brown").id(444L)
                .dateOfBirth(LocalDate.of(1881, 5, 20)).build();
        Book bookInBase = Book.builder().authorId(555L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").author(authorByIdFromBookInBase)
                .yearOfPublication(LocalDate.of(1912, 5, 20)).price(200L).cost(50L).barCode(1124)
                .id(314L).build();
        BookRespDTO bookRespDTO  = BookRespDTO.builder().title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .barCode(1124).id(314L).build();
        BookReqDTO bookReqDTO = BookReqDTO.builder().title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(LocalDate.of(1912, 5, 20))
                .cost(50L).barCode(1124).authorId(555L).id(314L)
                .build();
        Mockito.when(mapperForBook.toBookRespDTO(bookInBase))
                .thenReturn(bookRespDTO);
        Mockito.when(bookRepository.findById(314L))
                .thenReturn(Optional.of(bookInBase));
        Optional<BookRespDTO> update = bookService.update(314L, bookReqDTO);
        Assertions.assertEquals(update.get().getTitle(), bookReqDTO.getTitle());

    }

    @Test
    void delete() {
        Book bookInBase = Book.builder().title("Book").authorId(125L).id(555L).cover("soft").publishingHouse("Home")
                .pages(500).build();
        Mockito.when(bookRepository.findById(bookInBase.getId()))
                .thenReturn(Optional.of(bookInBase));
        Mockito.when(mapperForBook.toBookRespDTO(bookInBase)).thenReturn(BookRespDTO.builder().title("Book")
                .id(555L).cover("soft").publishingHouse("Home")
                .pages(500).build());
        Optional<BookRespDTO> deleteBook = bookService.delete(bookInBase.getId());
        Assertions.assertNotNull(deleteBook);
        Assertions.assertEquals(bookInBase.getTitle(), deleteBook.get().getTitle());
        Assertions.assertEquals(bookInBase.getCover(), deleteBook.get().getCover());
        Assertions.assertEquals(bookInBase.getPages(), deleteBook.get().getPages());
    }
}