package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class BookRepositories {
    private final List<Book> myBooks = new ArrayList<>();
    private Random r = new Random();

    public List<Book> findAll() {
        return myBooks;
    }

    public Book findById(long id) {
        return myBooks.stream()
                .filter(b->b.getId() == id)
                .findAny().get();
    }

    public Book save(Book book) {
        myBooks.add(book);
        book.setId(r.nextLong());
        return book;
    }

    public Book remove(long id) {
        Book byId = findById(id);
        myBooks.remove(byId);
        return byId;
    }
}
