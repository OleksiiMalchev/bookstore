package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AuthorRepositories {
    private final Map<Long, Author> baseAuthor = new HashMap<>();
    private final Random randomID = new Random();

    public List<Author> getAllAuthor() {
        return baseAuthor.values()
                .stream()
                .collect(Collectors.toList());
    }

    public Optional<Author> getAuthorByID(Long ID) {
        return Optional.ofNullable(baseAuthor.get(ID));

    }

    public Author saveAuthorInBase(Author author) {
        if (author != null) {
            author.setID(randomID.nextLong());
            baseAuthor.put(author.getID(), author);
            return author;
        }
        return null;
    }

    public Author updateAuthorByID(Long ID, Author author) {
        if (baseAuthor.containsKey(ID)) {
            author.setID(ID);
            baseAuthor.put(author.getID(), author);
            return baseAuthor.get(author.getID());
        }
        return null;
    }

    public Optional<Author> deleteAuthorByID(Long ID) {
        return Optional.ofNullable(baseAuthor.remove(ID));
    }



    public Author findAuthorByBook(Long idBook) {
        Book bookByID = baseAuthor.entrySet()
                .stream()
                .flatMap(a -> a.getValue()
                        .getBooks()
                        .stream())
                .filter(f -> f.getId().equals(idBook))
                .findAny()
                .orElse(null);
        return Optional.ofNullable(bookByID).stream()
                .map(Book::getAuthor).findAny().orElse(null);
    }
}
