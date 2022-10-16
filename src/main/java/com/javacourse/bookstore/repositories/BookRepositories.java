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

    public Optional<Book> findById(Long idBook) {
        return authorRepositories.getBaseAuthor()
                .entrySet()
                .stream()
                .flatMap(a -> a.getValue()
                        .getBooks()
                        .stream())
                .filter(f -> f.getID().equals(idBook))
                .findAny();
    }

    public Book save(Book book) {
        return authorRepositories.getAuthorByID(book.getAuthorID())
                .map(a -> a.addBook(book))
                .stream()
                .peek(b -> b.setAuthor(authorRepositories.getAuthorByID(book.getAuthorID()).get()))
                .findAny()
                .orElse(null);

    }

    public Book update(Long id, Book book) {
        if (authorRepositories.getBaseAuthor().containsKey(book.getAuthorID()) && id!=null) {
            Long id1 = findById(id).get().getAuthorID();
            Long id2 = book.getAuthorID();
            book.setESBI(findById(id).get().getESBI());
            book.setID(id);
            if (id1 == id2) {
                book.setAuthor(findById(id).get().getAuthor());
                return book;
            } else {
                findById(id).get().getAuthor().delete(findById(id).get());
                 return save(book);
            }
        }
        return null;
    }

    public Optional<Book> remove(Long id) {
        return findById(id)
                .map(b -> authorRepositories.getAuthorByID(b.getAuthorID()).get())
                .map(a -> a.delete(findById(id).get()));
    }
}
