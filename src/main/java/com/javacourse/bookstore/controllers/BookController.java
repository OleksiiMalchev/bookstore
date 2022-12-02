package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.mappers.domain.dto.BookReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import com.javacourse.bookstore.services.BookService;
import com.javacourse.bookstore.services.SomeСlass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final SomeСlass someСlass;

    @GetMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> getBookByID(@PathVariable("id") Long id) {
        someСlass.doSomething();
        Optional<BookRespDTO> bookById = bookService.getBookById(id);
        if (bookById.isPresent()) {
            return ResponseEntity.status(200).body(bookById);
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/booksAuthor/{authorId}")
    public ResponseEntity<? super List<BookRespDTO>> findAllBooksByAuthorId(@PathVariable("authorId") Long authorId) {
        someСlass.doSomething();
        List<BookRespDTO> bookRespDTOS = bookService.allBooksAuthor(authorId);
        if (bookRespDTOS.isEmpty()) {
            return new ResponseEntity<>("Books not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(bookRespDTOS);
    }


    @GetMapping("/books")
    public ResponseEntity<? super List<BookRespDTO>> allBooks() {
        someСlass.doSomething();
        List<BookRespDTO> bookRespDTOS = bookService.allBooks();
        if (bookRespDTOS.isEmpty()) {
            return new ResponseEntity<>("Books not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(bookRespDTOS);
    }

    @PostMapping("/books")
    public ResponseEntity<? super BookRespDTO> create(@RequestBody(required = false) BookReqDTO bookReqDTO) {
        someСlass.doSomething();
        Optional<BookRespDTO> bookRespDTO = bookService.create(bookReqDTO);
        if (bookRespDTO.isPresent()) {
            return ResponseEntity.status(201).body(bookRespDTO);
        }
        return new ResponseEntity<>("Invalid request. Book not create", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> update(@PathVariable("id") Long id,
                                                      @RequestBody(required = false) BookReqDTO bookReqDTO) {
        someСlass.doSomething();
        Optional<BookRespDTO> update = bookService.update(id, bookReqDTO);
        if (update.isPresent()) {
            return ResponseEntity.status(200).body(update);
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> delete(@PathVariable("id") Long id) {
        someСlass.doSomething();
        Optional<BookRespDTO> delete = bookService.delete(id);
        if (delete.isPresent()) {
            return ResponseEntity.status(200).body(delete);
        }
        return new ResponseEntity<>("Book not found ", HttpStatus.NOT_FOUND);
    }
}
