package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class BookRepositoriesTest {
    private final AuthorRepositories authorRepositories = new AuthorRepositories();
    private final BookRepositories bookRepositories = new BookRepositories(authorRepositories);


    @Test
    void findAll() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorDan = new Author("Dan", "Brown", 1964);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        Author authorInBase1 = authorRepositories.saveAuthorInBase(authorDan);
        Book saveBook = bookRepositories.save(new Book("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 1124, 314));
        Book saveBook1 = bookRepositories.save(new Book("The Holiday Round", authorInBase1.getID(), "hard", "XZ",
                1912, 300L, 50, 5247, 132));
        Book saveBook2 = bookRepositories.save(new Book("Once a Week", authorInBase.getID(), "soft", "XZ",
                1914, 350L, 50, 5354, 255));
        List<Book> allBooks = bookRepositories.findAll();
        Optional<Book> optionalBook = allBooks.stream()
                .filter(b -> b.getTitle().equals(saveBook.getTitle()))
                .findAny();
        Assertions.assertEquals(optionalBook.get().getTitle(),saveBook.getTitle());
        Optional<Book> optionalBook1 = allBooks.stream()
                .filter(b -> b.getTitle().equals(saveBook1.getTitle()))
                .findAny();
        Assertions.assertEquals(optionalBook1.get().getTitle(),saveBook1.getTitle());
        Optional<Book> optionalBook2 = allBooks.stream()
                .filter(b -> b.getTitle().equals(saveBook2.getTitle()))
                .findAny();
        Assertions.assertEquals(optionalBook2.get().getTitle(),saveBook2.getTitle());
    }

    @Test
    void findAllByAuthorID() {
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
        List<Book> allBooksByAuthorID = bookRepositories.findAllByAuthorID(authorInBase.getID());
        Assertions.assertEquals(allBooksByAuthorID,listBooksForTest);
    }

    @Test
    void findById() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        Book saveBook = bookRepositories.save(new Book("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 1124, 314));
        Optional<Book> bookById = bookRepositories.findById(saveBook.getID());
        Assertions.assertEquals(bookById.get(),saveBook);
    }

    @Test
    void save() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        Book saveBook = bookRepositories.save(new Book("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 1124, 314));
        Assertions.assertEquals(saveBook.getTitle(),"The Day's Play");
        Assertions.assertEquals(saveBook.getPages(),314);
    }

    @Test
    void update() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        Book saveBook = bookRepositories.save(new Book("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 1124, 314));
        Book updateBook = bookRepositories.save(new Book("The Holiday Round", authorInBase.getID(), "hard", "XZ",
                1912, 300L, 50, 5247, 132));

        Book bookAfterUpdate = bookRepositories.update(saveBook.getID(), updateBook);
        Assertions.assertEquals(bookAfterUpdate.getTitle(),"The Holiday Round");
        Assertions.assertEquals(bookAfterUpdate,updateBook);
        Assertions.assertEquals(bookAfterUpdate.getID(),updateBook.getID());
    }

    @Test
    void remove() {
        Author authorAlexander = new Author("Alexander", "Milne", 1882);
        Author authorInBase = authorRepositories.saveAuthorInBase(authorAlexander);
        Book saveBook = bookRepositories.save(new Book("The Day's Play", authorInBase.getID(), "soft", "XZ",
                1910, 200L, 50, 1124, 314));
        Optional<Book> bookDelete = bookRepositories.remove(saveBook.getID());
        Assertions.assertEquals(bookDelete.get(),saveBook);
        Assertions.assertEquals(bookDelete.get().getID(),saveBook.getID());

    }
}