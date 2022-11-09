package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
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
                        .author(mapperForAuthor.authorRespDTOID(author))
                        .cover(b.getCover())
                        .publishingHouse(b.getPublishingHouse())
                        .yearOfPublication(b.getYearOfPublication())
                        .price(b.getCost() * 2)
                        .barCode(b.getBarCode())
                        .id(b.getId())
                        .pages(b.getPages())
                        .ESBI(b.getESBI())
                        .build())
                .orElse(null);
    }
    public Book getBook(BookReqDTO bookReqDTO) {
        return Optional.ofNullable(bookReqDTO)
                .stream()
                .findAny()
                .map(b -> Book
                        .builder()
                        .title(b.getTitle())
                        .authorID(b.getAuthorID())
                        .cover(b.getCover())
                        .authorID(b.getAuthorID())
                        .publishingHouse(b.getPublishingHouse())
                        .yearOfPublication(b.getYearOfPublication())
                        .price(b.getCost() * 2)
                        .cost(b.getCost())
                        .barCode(b.getBarCode())
                        .pages(b.getPages())
                        .build())
                .orElse(null);
    }
}
