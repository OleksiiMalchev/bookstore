package com.javacourse.bookstore.services;

import com.javacourse.bookstore.entities.Books;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    public List<Books> allBooks() {
        return null;
    }  //GET/books - show all books
    public Books getBookByID(long id) {
        return null;
    }  //GET/books/{id}- display a book by id

    public Books create(BookService books) {
        return null;
    } //POST/books- create new book

    public Books upDate(long id, BookService books) {
        return null;
    }//PUT/books/{id}    - update a book by id

    public boolean delete(long id) {
        return true;
    }//DELETE/{id} - delete a book by id

}
