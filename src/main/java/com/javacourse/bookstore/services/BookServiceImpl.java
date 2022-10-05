package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.repositories.BookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public List<BookRespDTO> allBooksAuthor(Long ID){
        return bookRepositories.findAllByAuthorID(ID)
                .stream()
                .map(b->toBookRespDTO(b))
                .collect(Collectors.toList());
    }

    public BookRespDTO getBookById(Long id) {
        return Optional.ofNullable(bookRepositories.findById(id))
                .map(b -> toBookRespDTO(b))
                .orElse(null);
    }

    public BookRespDTO create(BookReqDTO bookReqDTO) {
        return Optional.ofNullable(getBook(bookReqDTO))
                .map(b -> bookRepositories.save(b))
                .map(b -> toBookRespDTO(b))
                .orElse(null);
    }

    public BookRespDTO update(Long id, BookReqDTO bookReqDTO) {
        return Optional.ofNullable(getBook(bookReqDTO))
                .map(b -> bookRepositories.update(id, b))
                .map(b -> toBookRespDTO(b))
                .orElse(null);
    }

    public BookRespDTO delete(Long id) {
        return Optional.ofNullable(bookRepositories.remove(id))
                .map(b -> toBookRespDTO(b))
                .orElse(null);
    }

    private BookRespDTO toBookRespDTO(Book book) {
        return Optional.ofNullable(book)
                .stream()
                .findAny()
                .map(b -> new BookRespDTO(b.getTitle(),
                        b.getAuthor(),
                        b.getCover(),
                        b.getPublishingHouse(),
                        b.getYearOfPublication(),
                        b.getCost() * 2,
                        b.getBarCode(),
                        b.getID(),
                        b.getPages(),
                        b.getESBI()))
                .orElse(null);

    }

    private Book getBook(BookReqDTO bookReqDTO) {
        return Optional.ofNullable(bookReqDTO)
                .stream()
                .findAny()
                .map(b -> new Book(b.getTitle(),
                        b.getAuthorID(),
                        b.getCover(),
                        b.getPublishingHouse(),
                        b.getYearOfPublication(),
                        b.getCost() * 2,
                        b.getCost(),
                        b.getBarCode(),
                        b.getPages()))
                .orElse(null);
    }
}
