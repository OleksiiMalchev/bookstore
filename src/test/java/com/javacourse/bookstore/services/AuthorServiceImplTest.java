package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOStock;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.mappers.MapperAuthorToRespDTO;
import com.javacourse.bookstore.mappers.MapperForAuthor;
import com.javacourse.bookstore.mappers.MapperForBook;
import com.javacourse.bookstore.repositories.AuthorRepositories;
import com.javacourse.bookstore.repositories.BookRepositories;
import exception.AuthorNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceImplTest {

    private AuthorRepositories authorRepositories = new AuthorRepositories();
    private MapperForAuthor mapperForAuthor = new MapperForAuthor();
    private MapperForBook mapperForBook = new MapperForBook(mapperForAuthor);
    private final MapperAuthorToRespDTO mapperAuthorToRespDTO = new MapperAuthorToRespDTO(mapperForBook);
    private AuthorServiceImpl authorServiceImpl = new AuthorServiceImpl(authorRepositories, mapperForAuthor, mapperAuthorToRespDTO);
    private BookRepositories bookRepositories = new BookRepositories(authorRepositories);

    @Test
    void getAllAuthor() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorDan = new Author("Dan", "Brown", 1964);
        Author authorJoanne = new Author("Joanne ", "Rowling", 1965);
        authorRepositories.saveAuthorInBase(authorAlexander);
        authorRepositories.saveAuthorInBase(authorDan);
        authorRepositories.saveAuthorInBase(authorJoanne);
        List<AuthorRespDTOStock> allAuthor = authorServiceImpl.getAllAuthor();
        Assertions.assertNotNull(allAuthor);
        Optional<AuthorRespDTOStock> authorRespDTO = allAuthor.stream()
                .filter(f -> f.getID().equals(authorAlexander.getID())).findAny();
        Assertions.assertEquals(authorRespDTO.get().getDateOfBirth(), authorAlexander.getDateOfBirth());

    }

    @Test
    void getAuthorByID() throws AuthorNotFoundException {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        authorRepositories.saveAuthorInBase(authorAlexander);
        AuthorRespDTOStock authorByID = authorServiceImpl.getAuthorByID(authorAlexander.getID());
        Assertions.assertNotNull(authorByID);
        Assertions.assertEquals(authorByID.getFirstName(), "Alexander");
    }

    @Test
    void createAuthor() {
        AuthorReqDTO authorReqDTO = new AuthorReqDTO("Alexander", "Milne", 1882);
        AuthorRespDTOStock authorRespDTOStock = authorServiceImpl.createAuthor(authorReqDTO);
        Assertions.assertNotNull(authorRespDTOStock);
        Assertions.assertEquals(authorRespDTOStock.getFirstName(), authorReqDTO.getFirstName());
        Assertions.assertEquals(authorRespDTOStock.getDateOfBirth(), authorReqDTO.getDateOfBirth());
    }

    @Test
    void updateAuthor() {
        AuthorReqDTO authorReqDTO = new AuthorReqDTO("Alexander", "Milne", 1882);
        AuthorReqDTO authorReqDTOUpdate = new AuthorReqDTO("Dan", "Brown", 1964);
        AuthorRespDTOStock authorRespDTOstock = authorServiceImpl.createAuthor(authorReqDTO);
        AuthorRespDTOStock authorRespDTOStockUpdate = authorServiceImpl.updateAuthor(authorRespDTOstock.getID(),authorReqDTOUpdate);
        Assertions.assertNotNull(authorRespDTOStockUpdate);
        Assertions.assertNotEquals(authorRespDTOStockUpdate.getFirstName(), authorReqDTO.getFirstName());
        Assertions.assertNotEquals(authorRespDTOStockUpdate.getDateOfBirth(), authorReqDTO.getDateOfBirth());
    }

    @Test
    void deleteAuthor() {
        Author authorForAdd = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorForAdd);
        AuthorRespDTOStock authorRespDTOStock = authorServiceImpl.deleteAuthor(authorInBase.getID());
        Assertions.assertEquals(authorRespDTOStock.getFirstName(),"Alexander");
    }

    @Test
    void findAuthorByBook() {
        Author authorForAdd = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorForAdd);
        Book saveBook = bookRepositories.save(new Book("", authorInBase.getID(), "", "",
                2020, 20L, 50, 50, 500));
        Author authorByBook = authorRepositories.findAuthorByBook(saveBook.getID());
        Assertions.assertNotNull(authorByBook);
        Assertions.assertEquals(authorByBook,authorForAdd);
    }

    @Test
    void getAuthorWithDetails() {
        Author authorForAdd = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorForAdd);
        AuthorRespDTOWithBooks authorWithDetails = authorServiceImpl.getAuthorWithDetails(authorInBase.getID());
        Assertions.assertNotNull(authorWithDetails);

    }
}