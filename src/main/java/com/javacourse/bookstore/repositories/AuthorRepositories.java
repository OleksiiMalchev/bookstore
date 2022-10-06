package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AuthorRepositories {
    private  Map<Long, Author> baseAuthor = new HashMap<>();
    private Random randomID = new Random();

    public List<Author> getAllAuthor() {
        return baseAuthor.values()
                .stream()
                .toList();
    }

    public Optional<Author> getAuthorByID(Long ID) {
        return Optional.ofNullable(baseAuthor.get(ID));

    }

    public Author saveAuthorInBase(Author author) {
        author.setID(randomID.nextLong());
        baseAuthor.put(author.getID(), author);
        return author;
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

    public Map<Long, Author> getBaseAuthor() {
        return baseAuthor;
    }

    public Author findAuthorByBook(Long id){
        Book bookByID = baseAuthor.entrySet()
                .stream()
                .flatMap(a -> a.getValue()
                        .getBooks()
                        .stream())
                .filter(f -> f.getID().equals(id))
                .findAny()
                .orElse(null);
       return Optional.ofNullable(bookByID).stream()
                .map(b->b.getAuthor()).findAny().orElse(null);
    }
}
