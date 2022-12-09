package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.mappers.domain.Author;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTOWithBooks;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperAuthorToRespDTO {
    @Autowired
    private final MapperForBook mapperForBook;

    public AuthorRespDTOWithBooks authorToRespDTO(Author author) {
        return Optional.ofNullable(author)
                .map(a -> AuthorRespDTOWithBooks
                        .builder()
                        .firstName(a.getFirstName())
                        .lastName(a.getLastName())
                        .id(a.getId())
                        .books(a.getBooks()
                                .stream()
                                .map(mapperForBook::bookRespDTOForAuthorWithBooks)
                                .collect(Collectors.toList()))
                        .build())
                .orElse(null);
    }
}
