package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepositories {
    private final AuthorRepositories authorRepositories;

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
        if (authorRepositories.getBaseAuthor().containsKey(book.getAuthorID())) {
            Long id1 = bookESBI.getAuthorID();
            Long id2 = book.getAuthorID();
            book.setESBI(bookESBI.getESBI());
            book.setID(id);
            if (id1 == id2) {
                book.setAuthor(bookESBI.getAuthor());
                return book;
            } else {
                bookESBI.getAuthor().delete(bookESBI);
                return save(book);
            }
        }
        return null;
    }

    public Optional<Book> remove(Long id) {
        return Optional.ofNullable(findById(id))
                .map(b -> authorRepositories.getAuthorByID(b.getAuthorID()).get())
                .map(a -> a.delete(findById(id)));
    }
}
