package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.services.BookService;
import com.javacourse.bookstore.services.SomeСlass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final SomeСlass someСlass;




    @GetMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> getBookByID(@PathVariable("id") Long id) {
        someСlass.doSomething();
        if (id != null) {
            return ResponseEntity.status(200).body(bookService.getBookById(id));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/booksAuthor/{authorID}")
    public ResponseEntity<? super List<BookRespDTO>> findAllByAuthorID(@PathVariable("authorID") Long authorID) {
        someСlass.doSomething();
        if (authorID != null) {
            return ResponseEntity.status(200).body(bookService.allBooksAuthor(authorID));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/books")
    public ResponseEntity<List<BookRespDTO>> allBooks() {
        someСlass.doSomething();
        return ResponseEntity.status(200).body(bookService.allBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<? super BookRespDTO> create(@RequestBody(required = false) BookReqDTO bookReqDTO) {
        someСlass.doSomething();
        if (bookReqDTO != null) {
            return ResponseEntity.status(201).body(bookService.create(bookReqDTO));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> update(@PathVariable("id") Long id,
                                                      @RequestBody(required = false) BookReqDTO bookReqDTO) {
        someСlass.doSomething();
        if (id != null && bookReqDTO != null) {
            return ResponseEntity.status(200).body(bookService.update(id, bookReqDTO));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> delete(@PathVariable("id") Long id) {
        someСlass.doSomething();
        if (id != null) {
            return ResponseEntity.status(200).body(bookService.delete(id));
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }
}
