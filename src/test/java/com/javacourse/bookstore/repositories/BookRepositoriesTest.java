package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookRepositoriesTest {
    @Autowired
    private BookRepositories bookRepositories;

    @Test
    void findAll() {

        Book saveBook = bookRepositories.save(Book.builder().authorID(555L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1910).price(200L).cost(50).barCode(1124)
                .id(314L).build());
        Book saveBook1 = bookRepositories.save(Book.builder().authorID(5555L).title("The Holiday Round")
                .cover("hard").publishingHouse("XZ").yearOfPublication(1912).price(300L).cost(50).barCode(5247)
                .id(314L).build());
        Book saveBook2 = bookRepositories.save(Book.builder().authorID(55555L).title("Once a Week")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1914).price(350L).cost(50).barCode(5247)
                .id(255L).build());
        List<Book> allBooks = bookRepositories.findAll();
        Optional<Book> optionalBook = allBooks.stream()
                .filter(b -> b.getTitle().equals(saveBook.getTitle()))
                .findAny();
        Assertions.assertEquals(optionalBook.get().getTitle(), saveBook.getTitle());
        Optional<Book> optionalBook1 = allBooks.stream()
                .filter(b -> b.getTitle().equals(saveBook1.getTitle()))
                .findAny();
        Assertions.assertEquals(optionalBook1.get().getTitle(), saveBook1.getTitle());
        Optional<Book> optionalBook2 = allBooks.stream()
                .filter(b -> b.getTitle().equals(saveBook2.getTitle()))
                .findAny();
        Assertions.assertEquals(optionalBook2.get().getTitle(), saveBook2.getTitle());
    }

    @Test
    void findAllByAuthorID() {

        Book saveBook = bookRepositories.save(Book.builder().authorID(444L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1910).price(200L).cost(50).barCode(1124)
                .id(314L).build());
        Book saveBook1 = bookRepositories.save(Book.builder().authorID(454L).title("The Holiday Round")
                .cover("hard").publishingHouse("XZ").yearOfPublication(1912).price(300L).cost(50).barCode(5247)
                .id(314L).build());
        Book saveBook2 = bookRepositories.save(Book.builder().authorID(444L).title("Once a Week")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1914).price(350L).cost(50).barCode(5247)
                .id(255L).build());
        List<Book> allBooksByAuthorID = bookRepositories.findAllByAuthorID(444L);
        Optional<Book> optionalBook = allBooksByAuthorID.stream()
                .filter(b -> b.getTitle().equals(saveBook.getTitle()))
                .findAny();
        Assertions.assertEquals(optionalBook.get().getTitle(), saveBook.getTitle());
        Book optionalBook1 = allBooksByAuthorID.stream()
                .filter(b -> b.getTitle().equals(saveBook1.getTitle()))
                .findAny().orElse(null);
        Assertions.assertNull(optionalBook1);
        Optional<Book> optionalBook2 = allBooksByAuthorID.stream()
                .filter(b -> b.getTitle().equals(saveBook2.getTitle()))
                .findAny();
        Assertions.assertEquals(optionalBook2.get().getTitle(), saveBook2.getTitle());

    }


    @Test
    void findById() {

        Book saveBook = bookRepositories.save(Book.builder().authorID(555L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1910).price(200L).cost(50).barCode(1124)
                .id(314L).build());
        Optional<Book> bookById = bookRepositories.findById(saveBook.getId());
        Assertions.assertEquals(bookById.get(), saveBook);
    }

    @Test
    void save() {

        Book saveBook = bookRepositories.save(Book.builder().authorID(4444L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1910).price(200L).cost(50).barCode(1124)
                .id(314L).build());
        Assertions.assertEquals(saveBook.getTitle(), "The Day's Play");
        Assertions.assertEquals(saveBook.getBarCode(), 1124);
    }

    @Test
    void update() {

        Book saveBook = bookRepositories.save(Book.builder().authorID(4444L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1910).price(200L).cost(50).barCode(1124)
                .id(314L).build());
        Book updateBook = bookRepositories.save(Book.builder().authorID(4444L).title("The Holiday Round")
                .cover("hard").publishingHouse("XZ").yearOfPublication(1912).price(300L).cost(50).barCode(5247)
                .id(314L).build());
        Book bookAfterUpdate = bookRepositories.update(saveBook.getId(), updateBook);
        Assertions.assertEquals(bookAfterUpdate.getTitle(), "The Holiday Round");
        Assertions.assertEquals(bookAfterUpdate, updateBook);
        Assertions.assertEquals(bookAfterUpdate.getId(), updateBook.getId());
    }

    @Test
    void updateWithOut() {

        Book saveBook = bookRepositories.save(Book.builder().authorID(4444L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1910).price(200L).cost(50).barCode(1124)
                .id(314L).build());
        Book updateBook = bookRepositories.save(Book.builder().authorID(4444L).title("The Holiday Round")
                .cover("hard").publishingHouse("XZ").yearOfPublication(1912).price(300L).cost(50).barCode(5247)
                .id(314L).build());

        Book bookAfterUpdate = bookRepositories.update(saveBook.getId(), updateBook);
        Assertions.assertEquals(bookAfterUpdate.getTitle(), "The Holiday Round");
        Assertions.assertEquals(bookAfterUpdate, updateBook);
        Assertions.assertEquals(bookAfterUpdate.getId(), updateBook.getId());
    }

    @Test
    void updateNull() {

        Book saveBook = bookRepositories.save(Book.builder().authorID(4444L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1910).price(200L).cost(50).barCode(1124)
                .id(314L).build());
        Book updateBook = bookRepositories.save(Book.builder().authorID(4444L).title("The Holiday Round")
                .cover("hard").publishingHouse("XZ").yearOfPublication(1912).price(300L).cost(50).barCode(5247)
                .id(314L).build());
        Book bookAfterUpdate = bookRepositories.update(null, updateBook);
        Assertions.assertNull(bookAfterUpdate);
        Book bookAfterUpdate1 = bookRepositories.update(saveBook.getId(), null);
        Assertions.assertNull(bookAfterUpdate1);
    }

    @Test
    void remove() {

        Book saveBook = bookRepositories.save(Book.builder().authorID(4444L).title("The Day's Play")
                .cover("soft").publishingHouse("XZ").yearOfPublication(1910).price(200L).cost(50).barCode(1124)
                .id(314L).build());
        Optional<Book> bookDelete = bookRepositories.remove(saveBook.getId());
        Assertions.assertEquals(bookDelete.get(), saveBook);
        Assertions.assertEquals(bookDelete.get().getId(), saveBook.getId());

    }
}