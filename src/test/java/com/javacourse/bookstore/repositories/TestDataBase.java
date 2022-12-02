package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest

public class TestDataBase {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void save(){
        Author alex = authorRepository.save(Author.builder().firstName("Alex").build());
        Assertions.assertNotNull(alex);
        Long id = alex.getId();
        Optional<Author> byId = authorRepository.findById(id);
        Assertions.assertTrue(byId.isPresent());
        Author author = byId.get();
        Assertions.assertNotNull(author.getId());
        Assertions.assertEquals("Alex",author.getFirstName());

    }
}
