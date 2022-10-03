package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AuthorRepositories {
    private final Map<Long, Author> baseAuthor = new HashMap<>();
    private Random randomID = new Random();

    public List<Author> getAllAuthor() {
        return baseAuthor.values()
                .stream()
                .toList();
    }

    public Author getAuthorByID(Long ID) {
        return baseAuthor.get(ID);
    }

    public Author saveAuthorInBase(Author author) {
        author.setID(randomID.nextLong());
        baseAuthor.put(author.getID(), author);
        return author;
    }

    public Author updateAuthorByID(Long ID, Author author) {
        if (baseAuthor.containsKey(ID)) {
            author.setID(ID);
            return baseAuthor.put(author.getID(), author);
        }
        return null;
    }

    public Author deleteAuthorByID(Long ID) {
        return baseAuthor.remove(ID);
    }
}
