package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.mappers.domain.Author;
import com.javacourse.bookstore.mappers.domain.Book;
import com.javacourse.bookstore.mappers.domain.dto.BookReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTOForAuthorWithBooks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperForBook {
    private final MapperForAuthor mapperForAuthor;

    public Book getBook(BookReqDTO bookReqDTO) {
        if (bookReqDTO != null) {
            return Book.builder()
                    .title(bookReqDTO.getTitle())
                    .authorId(bookReqDTO.getAuthorId())
                    .cover(bookReqDTO.getCover())
                    .publishingHouse(bookReqDTO.getPublishingHouse())
                    .yearOfPublication(bookReqDTO.getYearOfPublication())
                    .barCode(bookReqDTO.getBarCode())
                    .isbn(bookReqDTO.getIsbn())
                    .pages(bookReqDTO.getPages())
                    .build();
        }
        return null;
    }

    public BookRespDTO toBookRespDTO(Book book) {
        if (book != null) {
            Author author = book.getAuthor();
            return BookRespDTO.builder()
                    .title(book.getTitle())
                    .author(mapperForAuthor.authorToRespDTO(author))
                    .cover(book.getCover())
                    .publishingHouse(book.getPublishingHouse())
                    .yearOfPublication(book.getYearOfPublication())
                    .barCode(book.getBarCode())
                    .bookId(book.getId())
                    .pages(book.getPages())
                    .isbn(book.getIsbn())
                    .build();
        }
        return null;
    }

    public BookRespDTOForAuthorWithBooks bookRespDTOForAuthorWithBooks(Book book) {
        if (book != null) {
            Author author = book.getAuthor();
            return BookRespDTOForAuthorWithBooks.builder()
                    .title(book.getTitle())
                    .author(mapperForAuthor.authorRespDTOID(author))
                    .cover(book.getCover())
                    .publishingHouse(book.getPublishingHouse())
                    .yearOfPublication(book.getYearOfPublication())
                    .barCode(book.getBarCode())
                    .bookId(book.getId())
                    .pages(book.getPages())
                    .isbn(book.getIsbn())
                    .build();
        }
        return null;
    }
}
