package com.javacourse.bookstore.services;



import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookDto> allBooks();  //GET/books - show all books
    BookDto  getBookById(long id);  //GET/books/{id}- display a book by id
    BookDto  create(Book book); //POST/books- create new book
    BookDto  upDate(long id, Book book);//PUT/books/{id}    - update a book by id
    BookDto delete(long id);//DELETE/{id} - delete a book by id

}
