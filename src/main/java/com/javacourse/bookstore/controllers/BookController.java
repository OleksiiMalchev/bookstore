package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<? super BookRespDTO> getBookByID(@PathVariable("id") Long id) {
        if (id != null) {
            return ResponseEntity.status(200).body(bookServiceImpl.getBookById(id));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/booksAuthor/{authorID}")
    public ResponseEntity<? super List<BookRespDTO>> findAllByAuthorID(@PathVariable("authorID") Long authorID) {
        if (authorID != null) {
            return ResponseEntity.status(200).body(bookServiceImpl.allBooksAuthor(authorID));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/books")
    public ResponseEntity<List<BookRespDTO>> allBooks() {
        return ResponseEntity.status(200).body(bookServiceImpl.allBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<? super BookRespDTO> create(@RequestBody(required = false) BookReqDTO bookReqDTO) {
        if (bookReqDTO != null) {
            return ResponseEntity.status(201).body(bookServiceImpl.create(bookReqDTO));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> update(@PathVariable("id") Long id, @RequestBody(required = false) BookReqDTO bookReqDTO) {
        if (id != null && bookReqDTO != null) {
            return ResponseEntity.status(200).body(bookServiceImpl.update(id, bookReqDTO));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> delete(@PathVariable("id") Long id) {
        if (id != null) {
            return ResponseEntity.status(200).body(bookServiceImpl.delete(id));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }

}
