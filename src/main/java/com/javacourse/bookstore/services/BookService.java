package com.javacourse.bookstore.services;


import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookRespDTO> allBooks();  //GET/books - show all books
    BookRespDTO getBookById(Long id);  //GET/books/{id}- display a book by id
    BookRespDTO create(BookReqDTO bookReqDTO); //POST/books- create new book
    BookRespDTO update(Long id, BookReqDTO bookReqDTO);//PUT/books/{id}    - update a book by id
    BookRespDTO delete(Long id);//DELETE/{id} - delete a book by id

}
