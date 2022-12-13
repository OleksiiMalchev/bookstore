package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.mappers.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.services.AuthorService;
import exception.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/authors/{id}")
    public ResponseEntity<? super AuthorRespDTO> getAuthorById(@PathVariable("id") Long idAuthor) {
        return checking(authorService.getAuthorById(idAuthor));
    }

    @GetMapping("/authorBooks/{idBook}")
    public ResponseEntity<? super AuthorRespDTO> getAuthorByBook(@PathVariable("idBook") Long idBook) {
        return checking(authorService.findAuthorByBook(idBook));
    }

    @GetMapping("/authors")
    public ResponseEntity<? super List<AuthorRespDTO>> allAuthors() throws SQLException {
        List<AuthorRespDTO> allAuthor = authorService.getAllAuthor();
        if (allAuthor.isEmpty()) {
            return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(200).body(allAuthor);
        }
    }

    @GetMapping("/authorWith/{idAuthor}")
    public ResponseEntity<? super AuthorRespDTOWithBooks> getAuthorWithDetails(@PathVariable("idAuthor") Long idAuthor) {
        Optional<AuthorRespDTOWithBooks> authorWithDetails = authorService.getAuthorWithDetails(idAuthor);
        if (authorWithDetails.isPresent()) {
            return ResponseEntity.status(200).body(authorWithDetails);
        }
        return new ResponseEntity<>("Authors not found ", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/authors")
    public ResponseEntity<? super AuthorRespDTO> create(@RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        return checking(authorService.createAuthor(authorReqDTO));
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<? super AuthorRespDTO> update(@PathVariable("id") Long id,
                                                        @RequestBody(required = false) AuthorReqDTO authorReqDTO) {
        Optional<AuthorRespDTO> authorRespDTO = authorService.updateAuthor(id, authorReqDTO);
        if (authorRespDTO.isPresent()) {
            return ResponseEntity.status(201).body(authorRespDTO);
        }
        return new ResponseEntity<>("Author not found. No action taken.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<? super AuthorRespDTO> delete(@PathVariable("id") Long id) throws AuthorNotFoundException {
        return checking(authorService.deleteAuthor(id));
    }

    private static ResponseEntity<? super AuthorRespDTO> checking(Optional<AuthorRespDTO> authorRespDTO){
        if (authorRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(authorRespDTO);
        }
        return new ResponseEntity<>("Author not found. No action taken.", HttpStatus.NOT_FOUND);
    }
}
