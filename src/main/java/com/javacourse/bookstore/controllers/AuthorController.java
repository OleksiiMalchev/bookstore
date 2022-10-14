package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOStock;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.services.AuthorServiceImpl;
import exception.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorServiceImpl authorServiceImpl;

    @Autowired
    public AuthorController(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorRespDTOStock> getBookByID(@PathVariable("id") Long id) {
//        AuthorRespDTOStock authorByID = authorServiceImpl.getAuthorByID(id);
//        if (authorByID != null) {
//            return ResponseEntity.status(200).body(authorByID);
//        } else {
//            return new ResponseEntity<>("Author not found ", HttpStatus.NOT_FOUND);
//        }
        try {
            AuthorRespDTOStock authorByID = authorServiceImpl.getAuthorByID(id);
            return ResponseEntity.ok(authorByID);
        } catch (AuthorNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct Author Id", ex);
        }
    }

    @GetMapping("/authorBooks/{idBook}")
    public ResponseEntity<? super AuthorRespDTOStock> getAuthorByBook(@PathVariable("idBook") long idBook) {
        AuthorRespDTOStock authorByBook = authorServiceImpl.findAuthorByBook(idBook);
        if (authorByBook != null) {
            return ResponseEntity.status(200).body(authorByBook);
        } else {
            return new ResponseEntity<>("Author not found ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/authors")
    public ResponseEntity<? super List<AuthorRespDTOStock>> allAuthors() {
        List<AuthorRespDTOStock> allAuthor = authorServiceImpl.getAllAuthor();
        if (allAuthor != null) {
            return ResponseEntity.status(200).body(allAuthor);
        } else {
            return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/authorWith/{idAuthor}")
    public ResponseEntity<? super AuthorRespDTOWithBooks> getAuthorWithDetails(@PathVariable("idAuthor") long idAuthor) {
        AuthorRespDTOWithBooks authorWithDetails = authorServiceImpl.getAuthorWithDetails(idAuthor);
        if (authorWithDetails != null) {
            return ResponseEntity.status(200).body(authorWithDetails);
        }
        return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/authors")
    public ResponseEntity<? super AuthorRespDTOStock> create(@RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        if (authorReqDTO != null) {
           return ResponseEntity.status(201).body(authorServiceImpl.createAuthor(authorReqDTO));
        }
        return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<? super AuthorRespDTOStock> update(@PathVariable("id") Long id, @RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        if (authorReqDTO != null && id != null) {
            return ResponseEntity.status(200).body(authorServiceImpl.updateAuthor(id, authorReqDTO));
        }
        return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<? super AuthorRespDTOStock> delete(@PathVariable("id") Long id) {
        if (id != null) {
            return ResponseEntity.status(200).body(authorServiceImpl.deleteAuthor(id));
        }
        return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);
    }
}
