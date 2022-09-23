package com.javacourse.bookstore.controllers;
import com.javacourse.bookstore.entities.Books;
import com.javacourse.bookstore.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookServiceImpl books;

    @Autowired
    public BookController(BookServiceImpl books) {
        this.books = books;
    }

    @GetMapping("/books/{id}")
    public Books getBookByID(@PathVariable("id") long Id) {
        return books.getBookByID(Id);
    }

    @GetMapping("/books")
    public List<Books> allBooks() {
        return books.allBooks();
    }

    @PostMapping("/books")
    public Books create(BookServiceImpl books) {
        return books.create(books);
    }

    @PutMapping("/books/{id}")
    public Books update(@PathVariable("id") long id, BookServiceImpl books) {
        return books.upDate(id, books);
    }

}
