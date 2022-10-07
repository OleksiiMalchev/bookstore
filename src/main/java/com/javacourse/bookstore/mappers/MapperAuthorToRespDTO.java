package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MapperAuthorToRespDTO {
    private final MapperForBook mapperForBook;

    public MapperAuthorToRespDTO(MapperForBook mapperForBook) {
        this.mapperForBook = mapperForBook;
    }

    public AuthorRespDTOWithBooks authorToRespDTO(Author author) {
        return Optional.ofNullable(author)
                .map(a -> new AuthorRespDTOWithBooks(a.getFirstName(),
                        a.getLastName(),
                        a.getDateOfBirth(),
                        a.getID(),
                        a.getBooks()
                                .stream()
                                .map(mapperForBook::toBookRespDTO)
                                .collect(Collectors.toList())))
                .orElse(null);
    }


}
