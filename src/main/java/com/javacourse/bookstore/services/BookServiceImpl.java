package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.mappers.MapperForBook;
import com.javacourse.bookstore.repositories.BookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepositories bookRepositories;
    private final MapperForBook mapperForBook;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories, MapperForBook mapperForBook) {
        this.bookRepositories = bookRepositories;
        this.mapperForBook = mapperForBook;
    }


    public List<BookRespDTO> allBooks() {
        return bookRepositories.findAll().stream()
                .map(mapperForBook::toBookRespDTO)
                .collect(Collectors.toList());
    }

    public List<BookRespDTO> allBooksAuthor(Long ID) {
        return bookRepositories.findAllByAuthorID(ID)
                .stream()
                .map(mapperForBook::toBookRespDTO)
                .collect(Collectors.toList());
    }

    public BookRespDTO getBookById(Long id) {
        return bookRepositories.findById(id)
                .map(mapperForBook::toBookRespDTO)
                .orElse(null);
    }

    public BookRespDTO create(BookReqDTO bookReqDTO) {
        return Optional.ofNullable(mapperForBook.getBook(bookReqDTO))
                .map(bookRepositories::save)
                .map(mapperForBook::toBookRespDTO)
                .orElse(null);
    }

    public BookRespDTO update(Long id, BookReqDTO bookReqDTO) {
        return Optional.ofNullable(mapperForBook.getBook(bookReqDTO))
                .map(b -> bookRepositories.update(id, b))
                .map(mapperForBook::toBookRespDTO)
                .orElse(null);
    }

    public BookRespDTO delete(Long id) {
        return bookRepositories.remove(id)
                .map(mapperForBook::toBookRespDTO)
                .stream()
                .findAny()
                .orElse(null);
    }
}
