package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.mappers.MapperAuthorToRespDTO;
import com.javacourse.bookstore.mappers.MapperForAuthor;
import com.javacourse.bookstore.mappers.MapperForBook;
import com.javacourse.bookstore.repositories.AuthorRepositories;
import com.javacourse.bookstore.repositories.BookRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class BookServiceImplTest {
    private AuthorRepositories authorRepositories = new AuthorRepositories();
    private final BookRepositories bookRepositories = new BookRepositories(authorRepositories);
    private final MapperForAuthor mapperForAuthor = new MapperForAuthor();
    private final MapperForBook mapperForBook = new MapperForBook(mapperForAuthor);
    private MapperAuthorToRespDTO mapperAuthorToRespDTO = new MapperAuthorToRespDTO(mapperForBook);
    private BookServiceImpl bookService = new BookServiceImpl(bookRepositories, mapperForBook);


    @Test
    void allBooks() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        Book saveBook = bookRepositories.save(new Book("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 1124, 314));
        Book saveBook1 = bookRepositories.save(new Book("The Holiday Round", authorInBase.getID(), "hard", "XZ",
                1912, 300L, 50, 5247, 132));
        Book saveBook2 = bookRepositories.save(new Book("Once a Week", authorInBase.getID(), "soft", "XZ",
                1914, 350L, 50, 5354, 255));
        List<BookRespDTO> bookRespDTOS = bookService.allBooks();
        Optional<BookRespDTO> bookRespDTO = bookRespDTOS.stream().filter(b -> b.getTitle().equals(saveBook.getTitle())).findAny();
        Assertions.assertEquals(bookRespDTO.get().getTitle(), saveBook.getTitle());
    }

    @Test
    void allBooksAuthor() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        Book saveBook = bookRepositories.save(new Book("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 1124, 314));
        Book saveBook1 = bookRepositories.save(new Book("The Holiday Round", authorInBase.getID(), "hard", "XZ",
                1912, 300L, 50, 5247, 132));
        Book saveBook2 = bookRepositories.save(new Book("Once a Week", authorInBase.getID(), "soft", "XZ",
                1914, 350L, 50, 5354, 255));
        List<Book> listBooksForTest = new ArrayList<>();
        listBooksForTest.add(saveBook);
        listBooksForTest.add(saveBook1);
        listBooksForTest.add(saveBook2);
        List<BookRespDTO> allBooksByAuthorID = bookService.allBooksAuthor(authorInBase.getID());
        Optional<BookRespDTO> bookInList = allBooksByAuthorID.stream()
                .filter(b -> b.getID().equals(saveBook.getID()))
                .findAny();
        Assertions.assertEquals(bookInList.get().getBarCode(), saveBook.getBarCode());
        Optional<BookRespDTO> bookInList1 = allBooksByAuthorID.stream()
                .filter(b -> b.getID().equals(saveBook1.getID()))
                .findAny();
        Assertions.assertEquals(bookInList1.get().getPages(), saveBook1.getPages());
        Optional<BookRespDTO> bookInList2 = allBooksByAuthorID.stream()
                .filter(b -> b.getID().equals(saveBook2.getID()))
                .findAny();
        Assertions.assertEquals(bookInList2.get().getCover(), saveBook2.getCover());

    }

    @Test
    void getBookById() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        Book saveBook = bookRepositories.save(new Book("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 1124, 314));
        BookRespDTO bookById = bookService.getBookById(saveBook.getID());
        Assertions.assertNotNull(bookById);
    }

    @Test
    void create() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        BookReqDTO saveBookReqDTO = new BookReqDTO("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 314);
        BookRespDTO bookRespDTO = bookService.create(saveBookReqDTO);
        Assertions.assertNotNull(bookRespDTO);
        Assertions.assertEquals(saveBookReqDTO.getPages(), bookRespDTO.getPages());
        Assertions.assertEquals(saveBookReqDTO.getBarCode(), bookRespDTO.getBarCode());
        Assertions.assertEquals(saveBookReqDTO.getTitle(), bookRespDTO.getTitle());

    }

    @Test
    void update() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        BookReqDTO saveBookReqDTO = new BookReqDTO("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 314);
        BookRespDTO bookRespDTO = bookService.create(saveBookReqDTO);
        BookReqDTO updateBookReqDTO = new BookReqDTO("The Day's Play", authorInBase.getID(), "hard", "XZ",
                1910, 200L, 50, 314);
        BookRespDTO update = bookService.update(bookRespDTO.getID(), updateBookReqDTO);
        Assertions.assertNotNull(update);
        Assertions.assertNotEquals(update.getCover(), bookRespDTO.getCover());
    }

    @Test
    void delete() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        BookReqDTO saveBookReqDTO = new BookReqDTO("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 314);
        BookRespDTO bookRespDTO = bookService.create(saveBookReqDTO);
        BookRespDTO delete = bookService.delete(bookRespDTO.getID());
        Assertions.assertNotNull(delete);
        BookRespDTO deleteAgain = bookService.delete(bookRespDTO.getID());
        Assertions.assertNull(deleteAgain);
    }
}