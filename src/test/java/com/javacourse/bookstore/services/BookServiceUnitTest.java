package com.javacourse.bookstore.services;

import com.javacourse.bookstore.configuration.TestLightConfig;
import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.repositories.AuthorRepository;
import com.javacourse.bookstore.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest

public class BookServiceUnitTest extends TestLightConfig {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    final Long testAuthorId = 1L;
    final Long testId = 11L;
    final String testTitle = "A Game of Thrones";
    final String testCover = "soft";
    final String testPublishingHouse = "Penguin Books";
    final Integer testBarCode = 10001;
    final Integer testPages = 720;
    final Integer testIsbn = 553386794;
    final LocalDate testYearOfPublication = LocalDate.of(2011, 3, 25);
    final Optional<Author> testAuthor = Optional.of(Author.builder().id(1L).build());
    final AuthorRespDTO testAuthorRespDTO = AuthorRespDTO.builder().authorId(testAuthorId).build();

    @Test
    void createBook() {

        BookReqDTO bookReqDTO = BookReqDTO.builder()
                .authorId(testAuthorId)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .build();

        Book bookToSave = Book.builder()
                .authorId(testAuthorId)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .author(testAuthor.get())
                .build();

        when(authorRepository.existsById(testAuthorId)).thenReturn(true);
        when(authorRepository.findById(bookReqDTO.getAuthorId())).thenReturn(testAuthor);
        when(bookRepository.save(any(Book.class))).thenReturn(bookToSave);
        Optional<BookRespDTO> book = bookService.create(bookReqDTO);
        verify(bookRepository, times(1)).save(bookArgumentCaptor.capture());
        Book bookValue = bookArgumentCaptor.getValue();

        Assertions.assertEquals(testAuthorId, bookValue.getAuthorId());
        Assertions.assertEquals(testTitle, bookValue.getTitle());
        Assertions.assertEquals(testCover, bookValue.getCover());
        Assertions.assertEquals(testPublishingHouse, bookValue.getPublishingHouse());
        Assertions.assertEquals(testBarCode, bookValue.getBarCode());
        Assertions.assertEquals(testPages, bookValue.getPages());
        Assertions.assertEquals(testIsbn, bookValue.getIsbn());
        Assertions.assertEquals(testYearOfPublication, bookValue.getYearOfPublication());
        Assertions.assertTrue(book.isPresent());
        Assertions.assertEquals(testAuthorId, book.get().getAuthor().getAuthorId());
        Assertions.assertEquals(testTitle, book.get().getTitle());
        Assertions.assertEquals(testCover, book.get().getCover());
        Assertions.assertEquals(testPublishingHouse, book.get().getPublishingHouse());
        Assertions.assertEquals(testBarCode, book.get().getBarCode());
        Assertions.assertEquals(testPages, book.get().getPages());
        Assertions.assertEquals(testIsbn, book.get().getIsbn());
        Assertions.assertEquals(testYearOfPublication, book.get().getYearOfPublication());


    }

    @Test
    void createBookWhenAuthorIdNull() {

        BookReqDTO bookReqDTO = BookReqDTO.builder()
                .authorId(null)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .build();

        Book bookToSave = Book.builder()
                .authorId(null)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .build();

        when(bookRepository.save(any(Book.class))).thenReturn(bookToSave);
        Optional<BookRespDTO> book = bookService.create(bookReqDTO);
        Assertions.assertFalse(book.isPresent());


    }

    @Test
    void updateBook() {

        BookReqDTO bookReqDTO = BookReqDTO.builder()
                .authorId(testAuthorId)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .build();

        Book bookInBase = Book.builder()
                .authorId(testAuthorId)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .author(testAuthor.get())
                .id(testId)
                .build();

        when(bookRepository.findById(testId)).thenReturn(Optional.of(bookInBase));
        when(authorRepository.existsById(testAuthorId)).thenReturn(true);
        when(authorRepository.findById(testAuthorId)).thenReturn(testAuthor);
        Optional<BookRespDTO> book = bookService.update(testId, bookReqDTO);
        verify(bookRepository, times(1)).findById(testId);
        Assertions.assertTrue(book.isPresent());
        Assertions.assertEquals(testAuthorId, book.get().getAuthor().getAuthorId());
        Assertions.assertEquals(testTitle, book.get().getTitle());
        Assertions.assertEquals(testCover, book.get().getCover());
        Assertions.assertEquals(testPublishingHouse, book.get().getPublishingHouse());
        Assertions.assertEquals(testBarCode, book.get().getBarCode());
        Assertions.assertEquals(testPages, book.get().getPages());
        Assertions.assertEquals(testIsbn, book.get().getIsbn());
        Assertions.assertEquals(testYearOfPublication, book.get().getYearOfPublication());
        when(authorRepository.existsById(testAuthorId)).thenReturn(false);
        Optional<BookRespDTO> bookNull = bookService.update(testId, bookReqDTO);
        Assertions.assertFalse(bookNull.isPresent());

    }

    @Test
    void delete() {

        Book bookInBase = Book.builder()
                .authorId(testAuthorId)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .author(testAuthor.get())
                .id(testId)
                .build();

        when(bookRepository.findById(bookInBase.getId())).thenReturn(Optional.of(bookInBase));
        Optional<BookRespDTO> deleteBook = bookService.delete(bookInBase.getId());

        Assertions.assertTrue(deleteBook.isPresent());
        Assertions.assertEquals(testAuthorId, deleteBook.get().getAuthor().getAuthorId());
        Assertions.assertEquals(testTitle, deleteBook.get().getTitle());
        Assertions.assertEquals(testCover, deleteBook.get().getCover());
        Assertions.assertEquals(testPublishingHouse, deleteBook.get().getPublishingHouse());
        Assertions.assertEquals(testBarCode, deleteBook.get().getBarCode());
        Assertions.assertEquals(testPages, deleteBook.get().getPages());
        Assertions.assertEquals(testIsbn, deleteBook.get().getIsbn());
        Assertions.assertEquals(testYearOfPublication, deleteBook.get().getYearOfPublication());
        Optional<BookRespDTO> deleteBookNull = bookService.delete(null);
        Assertions.assertFalse(deleteBookNull.isPresent());
    }

    @Test
    void getBookById() {


        Book bookInBase = Book.builder()
                .id(testId)
                .authorId(testAuthorId)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .author(testAuthor.get())
                .build();

        when(bookRepository.findById(bookInBase.getId())).thenReturn(Optional.of(bookInBase));
        Optional<BookRespDTO> bookById = bookService.getBookById(bookInBase.getId());

        Assertions.assertTrue(bookById.isPresent());
        Assertions.assertEquals(testAuthorId, bookById.get().getAuthor().getAuthorId());
        Assertions.assertEquals(testTitle, bookById.get().getTitle());
        Assertions.assertEquals(testCover, bookById.get().getCover());
        Assertions.assertEquals(testPublishingHouse, bookById.get().getPublishingHouse());
        Assertions.assertEquals(testBarCode, bookById.get().getBarCode());
        Assertions.assertEquals(testPages, bookById.get().getPages());
        Assertions.assertEquals(testIsbn, bookById.get().getIsbn());
        Assertions.assertEquals(testYearOfPublication, bookById.get().getYearOfPublication());

    }

    @Test
    void allBooksAuthor() {

        Book bookInBase = Book.builder()
                .authorId(testAuthorId)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .author(testAuthor.get())
                .id(testId)
                .build();

        when(bookRepository.findAllByAuthorId(testAuthorId))
                .thenReturn(List.of(bookInBase));

        List<BookRespDTO> listOfBookRespDTO = bookService.allBooksAuthor(testAuthorId);
        Assertions.assertEquals(1, listOfBookRespDTO.size());
        Assertions.assertEquals(testAuthorId, listOfBookRespDTO.get(0).getAuthor().getAuthorId());
        Assertions.assertEquals(testTitle, listOfBookRespDTO.get(0).getTitle());
        Assertions.assertEquals(testCover, listOfBookRespDTO.get(0).getCover());
        Assertions.assertEquals(testPublishingHouse, listOfBookRespDTO.get(0).getPublishingHouse());
        Assertions.assertEquals(testBarCode, listOfBookRespDTO.get(0).getBarCode());
        Assertions.assertEquals(testPages, listOfBookRespDTO.get(0).getPages());
        Assertions.assertEquals(testIsbn, listOfBookRespDTO.get(0).getIsbn());
        Assertions.assertEquals(testYearOfPublication, listOfBookRespDTO.get(0).getYearOfPublication());
    }

    @Test
    void allBooks() {

        Book bookInBase = Book.builder()
                .authorId(testAuthorId)
                .title(testTitle)
                .cover(testCover)
                .publishingHouse(testPublishingHouse)
                .barCode(testBarCode)
                .pages(testPages)
                .isbn(testIsbn)
                .yearOfPublication(testYearOfPublication)
                .author(testAuthor.get())
                .id(testId)
                .build();

        when(bookRepository.findAll()).thenReturn(List.of(bookInBase));
        List<BookRespDTO> listOfBookRespDTO = bookService.allBooks();
        Assertions.assertEquals(1, listOfBookRespDTO.size());
        Assertions.assertEquals(testAuthorId, listOfBookRespDTO.get(0).getAuthor().getAuthorId());
        Assertions.assertEquals(testTitle, listOfBookRespDTO.get(0).getTitle());
        Assertions.assertEquals(testCover, listOfBookRespDTO.get(0).getCover());
        Assertions.assertEquals(testPublishingHouse, listOfBookRespDTO.get(0).getPublishingHouse());
        Assertions.assertEquals(testBarCode, listOfBookRespDTO.get(0).getBarCode());
        Assertions.assertEquals(testPages, listOfBookRespDTO.get(0).getPages());
        Assertions.assertEquals(testIsbn, listOfBookRespDTO.get(0).getIsbn());
        Assertions.assertEquals(testYearOfPublication, listOfBookRespDTO.get(0).getYearOfPublication());
    }
}
