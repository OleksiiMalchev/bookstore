package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepositories {
    private AuthorRepositories authorRepositories;

    @Autowired
    public BookRepositories(AuthorRepositories authorRepositories) {
        this.authorRepositories = authorRepositories;
    }

    public List<Book> findAll() {
        return authorRepositories.getBaseAuthor()
                .entrySet()
                .stream()
                .flatMap(a -> a.getValue()
                        .getBooks()
                        .stream())
                .collect(Collectors.toList());
    }

    public List<Book> findAllByAuthorID(Long authorID) {
        return authorRepositories.getAuthorByID(authorID).getBooks();
    }

    public Book findById(Long id) {
        return authorRepositories.getBaseAuthor()
                .entrySet()
                .stream()
                .flatMap(a -> a.getValue()
                        .getBooks()
                        .stream())
                .filter(f -> f.getID().equals(id))
                .findAny()
                .orElse(null);
    }

    public Book save(Book book) {
        Long authorID = book.getAuthorID();
        Author authorByID = authorRepositories.getAuthorByID(authorID);
        book.setAuthor(authorByID);
        authorByID.addBook(book);
        return book;
    }

    public Book update(Long id, Book book) {
        Book bookESBI = findById(id);
        book.setESBI(bookESBI.getESBI());
        book.setID(id);
        return save(book);

    }

    public Book remove(Long id) {
        Book bookForRemove = findById(id);
        Author authorRemove = authorRepositories.getAuthorByID(bookForRemove.getAuthorID());
        authorRemove.delete(bookForRemove);
        return bookForRemove;
    }
}
