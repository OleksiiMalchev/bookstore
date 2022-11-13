package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.services.AuthorServiceImpl;
import exception.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
public class AuthorController {
    private final AuthorServiceImpl authorServiceImpl;

    @Autowired
    public AuthorController(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorRespDTO> getBookByID(@PathVariable("id") Long id) {
        try {
            AuthorRespDTO authorByID = authorServiceImpl.getAuthorByID(id);
            return ResponseEntity.ok(authorByID);
        } catch (AuthorNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct Author Id",ex);
        }
    }

    @GetMapping("/authorBooks/{idBook}")
    public ResponseEntity<? super AuthorRespDTO> getAuthorByBook(@PathVariable("idBook") long idBook) {
        AuthorRespDTO authorByBook = authorServiceImpl.findAuthorByBook(idBook);
        if (authorByBook != null) {
            return ResponseEntity.status(200).body(authorByBook);
        } else {
            return new ResponseEntity<>("Author not found ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/authors")
    public ResponseEntity<? super List<AuthorRespDTO>> allAuthors() throws SQLException {
        List<AuthorRespDTO> allAuthor = authorServiceImpl.getAllAuthor();
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
    public ResponseEntity<? super AuthorRespDTO> create(@RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        if (authorReqDTO != null) {
           return ResponseEntity.status(201).body(authorServiceImpl.createAuthor(authorReqDTO));
        }
        return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<? super AuthorRespDTO> update(@PathVariable("id") Long id, @RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        if (authorReqDTO != null && id != null) {
            return ResponseEntity.status(200).body(authorServiceImpl.updateAuthor(id, authorReqDTO));
        }
        return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<? super AuthorRespDTO> delete(@PathVariable("id") Long id) {
        if (id != null) {
            return ResponseEntity.status(200).body(authorServiceImpl.deleteAuthor(id));
        }
        return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);
    }
}
