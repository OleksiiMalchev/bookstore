package com.javacourse.bookstore.services.impl;

import com.javacourse.bookstore.mappers.MapperForBook;
import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.repositories.AuthorRepository;
import com.javacourse.bookstore.repositories.BookRepository;
import com.javacourse.bookstore.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final MapperForBook mapperForBook;
    private final AuthorRepository authorRepository;

    @Override
    public List<BookRespDTO> allBooks() {
        return bookRepository.findAll()
                .stream()
                .map(mapperForBook::toBookRespDTO)
                .toList();
    }

    @Override
    public List<BookRespDTO> allBooksAuthor(Long idAuthor) {
        return bookRepository.findAllByAuthorId(idAuthor)
                .stream()
                .map(mapperForBook::toBookRespDTO)
                .toList();
    }

    @Override
    public Optional<BookRespDTO> getBookById(Long idBook) {
        return bookRepository.findById(idBook)
                .map(mapperForBook::toBookRespDTO);

    }

    @Transactional
    @Override
    public Optional<BookRespDTO> create(BookReqDTO bookReqDTO) {
        Long authorId = bookReqDTO.getAuthorId();
        if (authorId == null) {
            return Optional.empty();
        } else if (authorRepository.existsById(authorId)) {
            Optional<Author> authorById = authorRepository.findById(bookReqDTO.getAuthorId());
            Book book = mapperForBook.getBook(bookReqDTO);
            book.setAuthor(authorById.get());
            return
                    Optional.of(bookRepository.save(book))
                            .map(mapperForBook::toBookRespDTO);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<BookRespDTO> update(Long idBook, BookReqDTO bookReqDTO) {
        Optional<Book> bookById = bookRepository.findById(idBook);
        Long authorId = bookReqDTO.getAuthorId();
        if (authorId == null) {
            return Optional.empty();
        } else if (authorRepository.existsById(authorId)) {
            return bookById.map(book -> {
                        book.setTitle(bookReqDTO.getTitle());
                        book.setPublishingHouse(bookReqDTO.getPublishingHouse());
                        book.setBarCode(bookReqDTO.getBarCode());
                        book.setPages(bookReqDTO.getPages());
                        book.setIsbn(bookReqDTO.getIsbn());
                        book.setCover(bookReqDTO.getCover());
                        book.setAuthorId(bookReqDTO.getAuthorId());
                       // book.setAuthor(authorRepository.findById(bookReqDTO.getAuthorId()).get());
                        book.setYearOfPublication(bookReqDTO.getYearOfPublication());
                        return book;
                    })
                    .map(mapperForBook::toBookRespDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<BookRespDTO> delete(Long idBook) {
        Optional<BookRespDTO> bookRespDTO = bookRepository.findById(idBook)
                .map(mapperForBook::toBookRespDTO);
        if (bookRespDTO.isPresent()) {
            bookRepository.deleteById(idBook);
        }
        return bookRespDTO;
    }
}