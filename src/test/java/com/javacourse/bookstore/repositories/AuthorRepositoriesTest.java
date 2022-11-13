package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorRepositoriesTest {
    @Autowired
    private AuthorRepositories authorRepositories;

    @Test
    void getAllAuthors() throws SQLException {
        Author authorAlexander = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        Author authorDan = Author.builder().firstName("Dan").lastName("Brown")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        Author authorJoanne = Author.builder().firstName("Joanne").lastName("Rowling")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        authorRepositories.saveAuthorInBase(authorAlexander);
        authorRepositories.saveAuthorInBase(authorDan);
        authorRepositories.saveAuthorInBase(authorJoanne);
        List<Author> listAuthors = authorRepositories.getAllAuthor();
        Optional<Author> optionalAuthor = listAuthors.stream()
                .filter(b -> b.getFirstName().equals(authorAlexander.getFirstName()))
                .findAny();
        Assertions.assertEquals(optionalAuthor.get().getFirstName(), authorAlexander.getFirstName());


        Optional<Author> optionalAuthor1 = listAuthors.stream()
                .filter(b -> b.getFirstName().equals(authorDan.getFirstName()))
                .findAny();
        Assertions.assertEquals(optionalAuthor1.get().getFirstName(), authorDan.getFirstName());


        Optional<Author> optionalAuthor2 = listAuthors.stream()
                .filter(b -> b.getFirstName().equals(authorJoanne .getFirstName()))
                .findAny();
        Assertions.assertEquals(optionalAuthor2.get().getFirstName(), authorJoanne.getFirstName());


    }


    @Test
    void getAuthorByID() {
        Author authorID = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        authorRepositories.saveAuthorInBase(authorID);
        Assertions.assertEquals(authorRepositories.getAuthorByID(authorID.getId()).get(), authorID);
    }

    @Test
    void saveAuthorInBase() {
        Author authorForAdd = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        authorRepositories.saveAuthorInBase(authorForAdd);
        Optional<Author> authorByID = authorRepositories.getAuthorByID(authorForAdd.getId());
        Assertions.assertNotNull(authorByID);
        Assertions.assertEquals(authorByID.get(), authorForAdd);
        Author saveAuthorInBase = authorRepositories.saveAuthorInBase(null);
        Assertions.assertNull(saveAuthorInBase);
    }

    @Test
    void updateAuthorByID() {
        Author authorForAdd = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        Author authorForUpdate = Author.builder().firstName("Dan").lastName("Brown")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        authorRepositories.saveAuthorInBase(authorForAdd);
        Author authorUpdate = authorRepositories.updateAuthorByID(authorForAdd.getId(), authorForUpdate);
        Assertions.assertEquals(authorUpdate, authorForUpdate);
    }

    @Test
    void updateAuthorByIDWithOut() {
        Author authorForAdd = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        Author authorForUpdate = Author.builder().firstName("Dan").lastName("Brown")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        Author authorUpdate = authorRepositories.updateAuthorByID(authorForAdd.getId(), authorForUpdate);
        Assertions.assertNull(authorUpdate);
    }

    @Test
    void deleteAuthorByID() {
        Author authorForAdd = Author.builder().firstName("Alexander").lastName("Milne")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        Author authorInBase = authorRepositories.saveAuthorInBase(authorForAdd);
        Optional<Author> authorDelete = authorRepositories.deleteAuthorByID(authorInBase.getId());
        Assertions.assertEquals(authorDelete.get(), authorForAdd);
    }

    @Test
    void findAuthorByBook() {
        Author addAuthor = Author.builder().firstName("Alexander").lastName("Milne").id(125L)
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        authorRepositories.saveAuthorInBase(addAuthor);
        Book testBook = Book.builder().author(addAuthor).authorID(addAuthor.getId()).id(555L).build();
        addAuthor.addBook(testBook);
        Author authorByBook = authorRepositories.findAuthorByBook(testBook.getId());
        Assertions.assertEquals(addAuthor, authorByBook);
    }
}