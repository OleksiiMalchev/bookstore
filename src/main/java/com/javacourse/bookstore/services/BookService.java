package com.javacourse.bookstore.services;


import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookRespDTO> allBooks();
    BookRespDTO getBookById(Long ID);
    BookRespDTO create(BookReqDTO bookReqDTO);
    BookRespDTO update(Long ID, BookReqDTO bookReqDTO);
    BookRespDTO delete(Long ID);

}
