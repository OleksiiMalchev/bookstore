package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Repository
public class AuthorRepositories {
    private final Map<Long, Author> myAuthor = new HashMap<>();

    Random random = new Random();

    public List<Author> findAll() {
    return myAuthor.values().stream().toList();
    }

    public Author findById(long id) {
       return myAuthor.get(id);
    }

    public Author save(Author author) {
    author.setId(random.nextLong());
    myAuthor.put(author.getId(), author);
    return author;
    }

    public Author update(Long id, Author author){
        myAuthor.put(id, author);
        return author;
    }

    public Author remove(long id) {
    Author byId = findById(id);
    myAuthor.remove(byId);
    return byId;
    }

}
