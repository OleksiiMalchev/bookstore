package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOStock;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.services.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorServiceImpl authorServiceImpl;

    @Autowired
    public AuthorController(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorRespDTOStock> getBookByID(@PathVariable("id") long id) {
        return ResponseEntity.status(200).body(authorServiceImpl.getAuthorByID(id));
    }
    @GetMapping("/authorBooks/{idBook}")
    public ResponseEntity<AuthorRespDTOStock> getAuthorByBook(@PathVariable("idBook") long idBook) {
        return ResponseEntity.status(200).body(authorServiceImpl.findAuthorByBook(idBook));
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorRespDTOStock>> allBooks() {
        return ResponseEntity.status(200).body(authorServiceImpl.getAllAuthor());
    }
    @GetMapping("/authorWith/{idAuthor}")
    public ResponseEntity<AuthorRespDTOWithBooks> getAuthorWithDetails(@PathVariable("idAuthor") long idAuthor) {
        return ResponseEntity.status(200).body(authorServiceImpl.getAuthorWithDetails(idAuthor));
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorRespDTOStock> create(@RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        return ResponseEntity.status(201).body(authorServiceImpl.createAuthor(authorReqDTO));

    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorRespDTOStock> update(@PathVariable("id") long id, @RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        return ResponseEntity.status(200).body(authorServiceImpl.updateAuthor(id, authorReqDTO));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<AuthorRespDTOStock> delete(@PathVariable("id") long id) {
        return ResponseEntity.status(200).body(authorServiceImpl.deleteAuthor(id));
    }
}
