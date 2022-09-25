package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Book;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepositories {
    private final Map<Long, Book> myBooks = new HashMap<>();
    private Random r = new Random();

    public List<Book> findAll() {
        return myBooks.values()
                .stream()
                .toList();
    }

    public Book findById(long id) {
        return myBooks.get(id);
    }

    public Book save(Book book) {
        book.setId(r.nextLong());
        myBooks.put(book.getId(), book);
        return book;
    }

    public Book update(Long id, Book book) {
        myBooks.put(id, book);
        return book;
    }

    public Book remove(long id) {
        Book byId = findById(id);
        myBooks.remove(id);
        return byId;
    }
}
