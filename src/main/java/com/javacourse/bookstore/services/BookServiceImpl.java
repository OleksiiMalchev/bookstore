package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookDto;
import com.javacourse.bookstore.repositories.BookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositories bookRepositories;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }


    public List<BookDto> allBooks() {
        List<Book> books = bookRepositories.findAll();
        List<BookDto> booksDto = new LinkedList<>();
        for (Book bookFor : books) {
            booksDto.add(new BookDto(bookFor.getId(), bookFor.getTitle(), bookFor.getCost()));
        }
        return booksDto;
    }  //GET/books - show all books

    public BookDto getBookById(long id) {
        Book book = bookRepositories.findById(id);
        return new BookDto(book.getId(), book.getTitle(), book.getCost());

    }  //GET/books/{id}- display a book by id

    public BookDto create(Book book) {
        Book createBook = bookRepositories.save(book);
        return new BookDto(createBook.getId(), createBook.getTitle(), createBook.getCost());
    } //POST/books- create new book

    public BookDto upDate(long id, Book book) {
        Book bookUpdate = bookRepositories.update(id,book);
        return new BookDto(bookUpdate.getId(),bookUpdate.getTitle(),bookUpdate.getCost());
    }//PUT/books/{id}    - update a book by id

    public BookDto delete(long id) {
        Book deleteBook = bookRepositories.remove(id);
        return new BookDto(deleteBook.getId(), deleteBook.getTitle(), deleteBook.getCost());
    }
    //DELETE/{id} - delete a book by id

}
