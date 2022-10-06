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
        return authorRepositories.getAuthorByID(authorID)
                .map(Author::getBooks)
                .orElse(Collections.emptyList());

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
        Author authorByID = authorRepositories.getAuthorByID(authorID).get();
        if (authorByID != null) {
            book.setAuthor(authorByID);
            authorByID.addBook(book);
            return book;
        }
        return null;
    }

    public Book update(Long id, Book book) {
        Book bookESBI = findById(id);
        if (bookESBI != null) {
            book.setESBI(bookESBI.getESBI());
            book.setID(id);
            book.setAuthor(bookESBI.getAuthor());
            return book;
        }
        return null;

    }

    public Book remove(Long id) {
        Book bookForRemove = findById(id);
        if(bookForRemove!=null){
            Author authorRemove = authorRepositories.getAuthorByID(bookForRemove.getAuthorID()).get();
            return authorRemove.delete(bookForRemove);
        }
        return null;
    }
}
