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

    public Book findById(Long id) {
        return myBooks.get(id);
    }

    public Book save(Book book) {
        book.setESBI(String.valueOf(r.nextInt()));
        book.setID(r.nextLong());
        myBooks.put(book.getID(), book);
        return book;
    }

    public Book update(Long id, Book book) {
        if (myBooks.containsKey(id)) {
            Book bookESBI = myBooks.get(id);
            book.setESBI(bookESBI.getESBI());
            book.setID(id);
            myBooks.put(book.getID(), book);
            return myBooks.get(book.getID());
        }
        return null;
    }

    public Book remove(Long id) {
        return myBooks.remove(id);
    }
}
