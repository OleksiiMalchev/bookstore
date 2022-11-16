package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.mappers.MapperForBook;
import com.javacourse.bookstore.repositories.BookRepositories;
import com.javacourse.bookstore.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepositories bookRepositories;

    private final BookRepository bookRepository;

    private final MapperForBook mapperForBook;

    public List<BookRespDTO> allBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(mapperForBook::toBookRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookRespDTO> allBooksAuthor(Long idAuthor) {
        return bookRepository.findAllByAuthorID(idAuthor)
                .stream()
                .map(mapperForBook::toBookRespDTO)
                .collect(Collectors.toList());
    }

    public BookRespDTO getBookById(Long idBook) {
        return bookRepository.findById(idBook)
                .map(mapperForBook::toBookRespDTO)
                .orElse(null);
    }

    public BookRespDTO create(BookReqDTO bookReqDTO) {
        Book bookFromReq = mapperForBook.getBook(bookReqDTO);
        Book saveInBase = bookRepository.save(bookFromReq);
        Book book = bookRepository.findById(saveInBase.getId()).get();
        return mapperForBook.toBookRespDTO(book);
    }

    public BookRespDTO update(Long idBook, BookReqDTO bookReqDTO) {
        Book bookInBase = bookRepository.findById(idBook).get();
        Book bookFromReq = mapperForBook.getBook(bookReqDTO);
        bookInBase.setPrice(bookFromReq.getPrice());
        bookInBase.setTitle(bookFromReq.getTitle());
        bookInBase.setCost(bookFromReq.getCost());
        bookInBase.setPublishingHouse(bookFromReq.getPublishingHouse());
        bookInBase.setBarCode(bookFromReq.getBarCode());
        bookInBase.setPages(bookFromReq.getPages());
        bookInBase.setIsbn(bookFromReq.getIsbn());
        bookInBase.setAuthorId(bookFromReq.getAuthorId());
        bookInBase.setYearOfPublication(bookFromReq.getYearOfPublication());
        bookRepository.save(bookInBase);
        return mapperForBook.toBookRespDTO(bookInBase);
    }

    public BookRespDTO delete(Long idBook) {
        Book bookInBase = bookRepository.findById(idBook).get();
        BookRespDTO bookRespDTO = mapperForBook.toBookRespDTO(bookInBase);
        bookRepository.delete(bookInBase);
        return bookRespDTO;
    }
}
