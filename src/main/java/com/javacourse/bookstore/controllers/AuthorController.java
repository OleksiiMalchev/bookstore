package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorDto;

import com.javacourse.bookstore.services.AuthorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @Autowired
    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") long Id) {
        return ResponseEntity.status(200).body(authorService.getAuthorById(Id));
    }

    @GetMapping("authors")
    public ResponseEntity<List<AuthorDto>> allAuthors() {
        return ResponseEntity.status(200).body(authorService.allAuthor());
    }

    @PostMapping("authors")
    public ResponseEntity<AuthorDto> create(@RequestBody Author author) {
        return ResponseEntity.status(201).body(authorService.create(author));
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorDto> upDate(@PathVariable("id") long id, Author author) {
        return ResponseEntity.status(200).body(authorService.upDate(id, author));
    }

}
