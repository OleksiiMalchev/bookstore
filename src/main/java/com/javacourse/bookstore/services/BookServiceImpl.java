package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookDto;
import com.javacourse.bookstore.repositories.BookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositories bookRepositories;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }


    public List<BookDto> allBooks() {
        List<Book> books = new ArrayList<>();
        List<BookDto> booksDto = new ArrayList<>();
        books.add(new Book("Book", 10));
        booksDto.add(new BookDto(15, "1", 60));
        return booksDto;
    }  //GET/books - show all books

    public BookDto getBookByID(long id) {
        Book book = bookRepositories.findById(id);
        return new BookDto(book.getId(), book.getTitle(), book.getCost());

    }  //GET/books/{id}- display a book by id

    public BookDto create(Book book) {
        Book createBook = bookRepositories.save(book);
        return new BookDto(createBook.getId(), createBook.getTitle(), createBook.getCost());
    } //POST/books- create new book

    public BookDto upDate(long id, Book book) {
        return null;
    }//PUT/books/{id}    - update a book by id

    public BookDto delete(long id) {
        Book deleteBook = bookRepositories.remove(id);
        return new BookDto(deleteBook.getId(), deleteBook.getTitle(), deleteBook.getCost());
    }
    //DELETE/{id} - delete a book by id

}
