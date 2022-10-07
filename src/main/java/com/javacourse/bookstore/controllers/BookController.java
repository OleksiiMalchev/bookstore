package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookServiceImpl bookServiceImpl;

    @Autowired
    public BookController(BookServiceImpl books) {
        this.bookServiceImpl = books;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookRespDTO> getBookByID(@PathVariable("id") long id) {
        return ResponseEntity.status(200).body(bookServiceImpl.getBookById(id));
    }
    @GetMapping("/booksAuthor/{authorID}")
    public ResponseEntity<List<BookRespDTO>> findAllByAuthorID(@PathVariable("authorID") long authorID) {
        return ResponseEntity.status(200).body(bookServiceImpl.allBooksAuthor(authorID));
    }


    @GetMapping("/books")
    public ResponseEntity<List<BookRespDTO>> allBooks() {
        return ResponseEntity.status(200).body(bookServiceImpl.allBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<BookRespDTO> create(@RequestBody(required = false) BookReqDTO bookReqDTO) {
            return ResponseEntity.status(201).body(bookServiceImpl.create(bookReqDTO));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookRespDTO> update(@PathVariable("id") long id, @RequestBody(required = false) BookReqDTO bookReqDTO) {
        return ResponseEntity.status(200).body(bookServiceImpl.update(id, bookReqDTO));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookRespDTO> delete(@PathVariable("id") long id) {
        return ResponseEntity.status(200).body(bookServiceImpl.delete(id));
    }
}
