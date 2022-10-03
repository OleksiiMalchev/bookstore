package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
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
    public ResponseEntity<AuthorRespDTO> getBookByID(@PathVariable("id") long id) {
        return ResponseEntity.status(200).body(authorServiceImpl.getAuthorByID(id));
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorRespDTO>> allBooks() {
        return ResponseEntity.status(200).body(authorServiceImpl.getAllAuthor());
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorRespDTO> create(@RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        return ResponseEntity.status(201).body(authorServiceImpl.createAuthor(authorReqDTO));

    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorRespDTO> update(@PathVariable("id") long id, @RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        return ResponseEntity.status(200).body(authorServiceImpl.updateAuthor(id, authorReqDTO));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<AuthorRespDTO> delete(@PathVariable("id") long id) {
        return ResponseEntity.status(200).body(authorServiceImpl.deleteAuthor(id));
    }
}
