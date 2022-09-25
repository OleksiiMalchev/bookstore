package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookDto;
import com.javacourse.bookstore.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl books) {
        this.bookService = books;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBookByID(@PathVariable("id") long Id) {
       return ResponseEntity.status(200).body(bookService.getBookById(Id));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto> > allBooks() {
        return ResponseEntity.status(200).body(bookService.allBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto>  create(@RequestBody Book book) {
        return ResponseEntity.status(201).body(bookService.create(book));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto>  update(@PathVariable("id") long id, Book book) {
        return ResponseEntity.status(200).body(bookService.upDate(id, book));
    }

}
