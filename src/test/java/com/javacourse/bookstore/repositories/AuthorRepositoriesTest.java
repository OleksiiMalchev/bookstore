package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class AuthorRepositoriesTest {
    private final AuthorRepositories authorRepositories = new AuthorRepositories();
    private final BookRepositories bookRepositories = new BookRepositories(authorRepositories);

    @Ignore
    @Test
    void getAllAuthorIfAuthorsAdd() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorDan = new Author("Dan", "Brown", 1964);
        Author authorJoanne = new Author("Joanne ", "Rowling", 1965);
        authorRepositories.saveAuthorInBase(authorAlexander);
        authorRepositories.saveAuthorInBase(authorDan);
        authorRepositories.saveAuthorInBase(authorJoanne);
        List<Author> listAuthors = authorRepositories.getAllAuthor();
        List<Author> listAuthorsTest = new ArrayList<>();
        listAuthorsTest.add(authorAlexander);
        listAuthorsTest.add(authorDan);
        listAuthorsTest.add(authorJoanne);
        Assertions.assertNotNull(listAuthorsTest);
        Assertions.assertEquals(listAuthors, listAuthorsTest);
    }

    @Test
    void getAllAuthorIfAuthorsNotAdd() {
        authorRepositories.saveAuthorInBase(null);
        List<Author> listAuthorsNoAuthors = authorRepositories.getAllAuthor();
        List<Author> clearListAuthor = new ArrayList<>();
        Assertions.assertEquals(listAuthorsNoAuthors, clearListAuthor);
    }


    @Test
    void getAuthorByID() {
        Author authorID = new Author("Alexander", "Milne", 1882);
        authorRepositories.saveAuthorInBase(authorID);
        Assertions.assertEquals(authorRepositories.getAuthorByID(authorID.getID()).get(), authorID);
    }

    @Test
    void saveAuthorInBase() {
        Author authorForAdd = new Author("Alexander", "Milne", 1882);
        authorRepositories.saveAuthorInBase(authorForAdd);
        Optional<Author> authorByID = authorRepositories.getAuthorByID(authorForAdd.getID());
        Assertions.assertNotNull(authorByID);
        Assertions.assertEquals(authorByID.get(), authorForAdd);
    }

    @Test
    void updateAuthorByID() {
        Author authorForAdd = new Author("Alexander", "Milne", 1882);
        Author authorForUpdate = new Author("Joanne ", "Rowling", 1965);
        authorRepositories.saveAuthorInBase(authorForAdd);
        Author authorUpdate = authorRepositories.updateAuthorByID(authorForAdd.getID(), authorForUpdate);
        Assertions.assertEquals(authorUpdate, authorForUpdate);
    }

    @Test
    void deleteAuthorByID() {
        Author authorForAdd = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorForAdd);
        Optional<Author> authorDelete = authorRepositories.deleteAuthorByID(authorInBase.getID());
        Assertions.assertEquals(authorDelete.get(), authorForAdd);
    }

    @Test
    void findAuthorByBook() {
        Author authorForAdd = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorForAdd);
        Book saveBook = bookRepositories.save(new Book("", authorInBase.getID(), "", "",
                2020, 20L, 50, 50, 500));
        Author authorByBook = authorRepositories.findAuthorByBook(saveBook.getID());
        Assertions.assertEquals(authorByBook, authorForAdd);
    }
}