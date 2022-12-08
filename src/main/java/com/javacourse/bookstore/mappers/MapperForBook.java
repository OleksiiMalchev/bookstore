package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.mappers.domain.Author;
import com.javacourse.bookstore.mappers.domain.Book;
import com.javacourse.bookstore.mappers.domain.dto.BookReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.BookRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MapperForBook {
    private final MapperForAuthor mapperForAuthor;


    public BookRespDTO toBookRespDTO(Book book) {
        Author author = book.getAuthor();
        return Optional.ofNullable(book)
                .stream()
                .findAny()
                .map(b -> BookRespDTO.builder()
                        .title(b.getTitle())
                        .author(mapperForAuthor.authorToRespDTOStock(author))
                        .cover(b.getCover())
                        .publishingHouse(b.getPublishingHouse())
                        .yearOfPublication(b.getYearOfPublication())
                        .barCode(b.getBarCode())
                        .bookId(b.getId())
                        .pages(b.getPages())
                        .isbn(b.getIsbn())
                        .build())
                .orElse(null);
    }
    public Optional<Book> getBook(BookReqDTO bookReqDTO) {
        return Optional.ofNullable(bookReqDTO)
                .stream()
                .findAny()
                .map(b -> Book
                        .builder()
                        .title(b.getTitle())
                        .authorId(b.getAuthorId())
                        .cover(b.getCover())
                        .publishingHouse(b.getPublishingHouse())
                        .yearOfPublication(b.getYearOfPublication())
                        .barCode(b.getBarCode())
                        .isbn(b.getIsbn())
                        .pages(b.getPages())
                        .build());
    }
}
