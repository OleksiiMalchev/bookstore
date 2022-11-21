package com.javacourse.bookstore.services;


import com.javacourse.bookstore.mappers.domain.dto.BookReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<BookRespDTO> allBooks();

    List<BookRespDTO> allBooksAuthor(Long id);

    Optional<BookRespDTO> getBookById(Long id);
    Optional<BookRespDTO> create(BookReqDTO bookReqDTO);
    Optional<BookRespDTO> update(Long id, BookReqDTO bookReqDTO);
    Optional<BookRespDTO> delete(Long id);



}
