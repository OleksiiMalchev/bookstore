package com.javacourse.bookstore.mappers;
import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.Book;
import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class MapperForBook {
    private final MapperForAuthor mapperForAuthor;

    public MapperForBook(MapperForAuthor mapperForAuthor) {
        this.mapperForAuthor = mapperForAuthor;
    }

    public BookRespDTO toBookRespDTO(Book book) {
        Author author  = book.getAuthor();
        return Optional.ofNullable(book)
                .stream()
                .findAny()
                .map(b -> new BookRespDTO(b.getTitle(),
                        mapperForAuthor.authorRespDTOID(author),
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
    public Book getBook(BookReqDTO bookReqDTO) {
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
