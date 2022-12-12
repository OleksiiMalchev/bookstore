package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.mappers.domain.dto.BookReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import com.javacourse.bookstore.services.BookService;
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

    @GetMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> getBookByID(@PathVariable("id") Long id) {
        return checking(bookService.getBookById(id));
    }

    @GetMapping("/booksAuthor/{authorId}")
    public ResponseEntity<? super List<BookRespDTO>> findAllBooksByAuthorId(@PathVariable("authorId") Long authorId) {
        return checkingBook(bookService.allBooksAuthor(authorId));
    }


    @GetMapping("/books")
    public ResponseEntity<? super List<BookRespDTO>> allBooks() {
        return checkingBook(bookService.allBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<? super BookRespDTO> create(@RequestBody(required = false) BookReqDTO bookReqDTO) {
        return checking(bookService.create(bookReqDTO));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> update(@PathVariable("id") Long id,
                                                      @RequestBody(required = false) BookReqDTO bookReqDTO) {
        return checking(bookService.update(id, bookReqDTO));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<? super BookRespDTO> delete(@PathVariable("id") Long id) {
        return checking(bookService.delete(id));

    }

    private static ResponseEntity<? super BookRespDTO> checking(Optional<BookRespDTO> bookRespDTO){
        if (bookRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(bookRespDTO);
        }
        return new ResponseEntity<>("Book not found. No action taken.", HttpStatus.NOT_FOUND);
    }

    private static ResponseEntity<? super List<BookRespDTO>> checkingBook(List<BookRespDTO> list) {
        if (list.isEmpty()) {
            return new ResponseEntity<>("Books not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(list);
    }
}
