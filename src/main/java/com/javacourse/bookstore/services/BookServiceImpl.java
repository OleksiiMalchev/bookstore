package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.repositories.BookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepositories bookRepositories;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }

    public List<BookRespDTO> allBooks() {
        return bookRepositories.findAll().stream()
                .map(b -> toBookRespDTO(b))
                .collect(Collectors.toList());
    }

    public BookRespDTO getBookById(Long id) {
        Book book = bookRepositories.findById(id);
        if (book != null) {
            return toBookRespDTO(book);
        }
        return null;
    }

    public BookRespDTO create(BookReqDTO bookReqDTO) {
        if (bookReqDTO != null) {
            Book newBook = getBook(bookReqDTO);
            Book save = bookRepositories.save(newBook);
            return toBookRespDTO(save);
        }
        return null;
    }

    public BookRespDTO update(Long id, BookReqDTO bookReqDTO) {
        if (bookReqDTO != null) {
            Book updateBook = getBook(bookReqDTO);
            Book update = bookRepositories.update(id, updateBook);
            return toBookRespDTO(update);
        }
        return null;
    }

    public BookRespDTO delete(Long id) {
        Book deleteBook = bookRepositories.remove(id);
        if (deleteBook != null) {
            BookRespDTO bookRespDTO = toBookRespDTO(deleteBook);
            return bookRespDTO;
        }
        return null;
    }

    private BookRespDTO toBookRespDTO(Book book) {
            return new BookRespDTO(book.getTitle(),
                    book.getAuthor(),
                    book.getCover(),
                    book.getPublishingHouse(),
                    book.getYearOfPublication(),
                    book.getCost() * 2,
                    book.getBarCode(),
                    book.getID(),
                    book.getPages(),
                    book.getESBI());
    }

    private Book getBook(BookReqDTO bookReqDTO) {
        Book newBook = new Book(bookReqDTO.getTitle(),
                bookReqDTO.getAuthor(),
                bookReqDTO.getCover(),
                bookReqDTO.getPublishingHouse(),
                bookReqDTO.getYearOfPublication(),
                bookReqDTO.getCost() * 2,
                bookReqDTO.getCost(),
                bookReqDTO.getBarCode(),
                bookReqDTO.getID(),
                bookReqDTO.getPages());
        return newBook;
    }
}
