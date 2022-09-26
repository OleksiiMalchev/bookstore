package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Book;

import com.javacourse.bookstore.domain.dto.BookDto;
import com.javacourse.bookstore.repositories.BookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositories bookRepositories;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }

//TODO string example; ArrayList vs LinkedList

//    public List<BookDto> allBooks() {
//        List<Book> books = bookRepositories.findAll();
//        List<BookDto> booksDto = new LinkedList<>();
//        for (Book bookFor : books) {
//            booksDto.add(new BookDto(bookFor.getId(), bookFor.getTitle(), bookFor.getCost()));
//        }
//        return booksDto;
//    }  //GET/books - show all books

    public List<BookDto> allBooks() {
        return bookRepositories.findAll()
                .stream()
                .map(r -> new BookDto(r.getId(), r.getTitle(), r.getCost()))
                .collect(Collectors.toList());
    }

    public BookDto getBookById(long id) {
        Book book = bookRepositories.findById(id);
        return new BookDto(book.getId(), book.getTitle(), book.getCost());

    }  //GET/books/{id}- display a book by id

    public BookDto create(Book book) {
        Book createBook = bookRepositories.save(book);
        return new BookDto(createBook.getId(), createBook.getTitle(), createBook.getCost());
    } //POST/books- create new book

    //TODO chek ID
    public BookDto upDate(long id, Book book) {
        Book bookUpdate = bookRepositories.update(id, book);
        return new BookDto(bookUpdate.getId(), bookUpdate.getTitle(), bookUpdate.getCost());
    }//PUT/books/{id}    - update a book by id

    //TODO chek NPE
    public BookDto delete(long id) {
        Book deleteBook = bookRepositories.remove(id);
        return new BookDto(deleteBook.getId(), deleteBook.getTitle(), deleteBook.getCost());
    }
    //DELETE/{id} - delete a book by id


}
