package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.domain.dto.BookReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import com.javacourse.bookstore.mappers.MapperForBook;
import com.javacourse.bookstore.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final MapperForBook mapperForBook;

    @Override
    public List<BookRespDTO> allBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(mapperForBook::toBookRespDTO)
                .toList();
    }

    @Override
    public List<BookRespDTO> allBooksAuthor(Long idAuthor) {
        return bookRepository.findAllByAuthorID(idAuthor)
                .stream()
                .map(mapperForBook::toBookRespDTO)
                .toList();
    }

    @Override
    public Optional<BookRespDTO> getBookById(Long idBook) {
        return bookRepository.findById(idBook)
                .map(mapperForBook::toBookRespDTO);

    }

    @Override
    public Optional<BookRespDTO> create(BookReqDTO bookReqDTO) {
        if (bookReqDTO.getCost() != null && bookReqDTO.getAuthorId() != null) {
            return mapperForBook.getBook(bookReqDTO)
                    .map(bookRepository::save)
                    .map(mapperForBook::toBookRespDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<BookRespDTO> update(Long idBook, BookReqDTO bookReqDTO) {
        return bookRepository.findById(idBook)
                .map(book -> {
                    if (bookReqDTO.getCost() != null) {
                        book.setPrice(bookReqDTO.getCost() * 2);
                        book.setCost(bookReqDTO.getCost());
                        book.setTitle(bookReqDTO.getTitle());
                        book.setCost(bookReqDTO.getCost());
                        book.setPublishingHouse(bookReqDTO.getPublishingHouse());
                        book.setBarCode(bookReqDTO.getBarCode());
                        book.setPages(bookReqDTO.getPages());
                        book.setIsbn(bookReqDTO.getIsbn());
                        book.setCover(bookReqDTO.getCover());
                        book.setAuthorId(bookReqDTO.getAuthorId());
                        book.setYearOfPublication(bookReqDTO.getYearOfPublication());
                        return book;
                    }
                    return null;
                })
                .map(mapperForBook::toBookRespDTO);
    }

    @Override
    public Optional<BookRespDTO> delete(Long idBook) {
        Optional<BookRespDTO> bookRespDTO = bookRepository.findById(idBook).map(mapperForBook::toBookRespDTO);
        if (bookRespDTO.isPresent()) {
            bookRepository.deleteById(idBook);
        }
        return bookRespDTO;
    }
}
