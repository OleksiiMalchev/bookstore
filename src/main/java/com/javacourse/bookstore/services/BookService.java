package com.javacourse.bookstore.services;

import com.javacourse.bookstore.entities.Books;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Books> allBooks();  //GET/books - show all books
    Books getBookByID(long id);  //GET/books/{id}- display a book by id
    Books create(BookService books); //POST/books- create new book
    Books upDate(long id, BookService books);//PUT/books/{id}    - update a book by id
    boolean delete(long id);//DELETE/{id} - delete a book by id

}
