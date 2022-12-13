package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperAuthorToRespDTO {
    @Autowired
    private final MapperForBook mapperForBook;

    public AuthorRespDTOWithBooks authorToRespDTO(Author author) {
        if (author != null) {
            return AuthorRespDTOWithBooks
                    .builder()
                    .firstName(author.getFirstName())
                    .lastName(author.getLastName())
                    .id(author.getId())
                    .books(author.getBooks().stream()
                            .map(mapperForBook::bookRespDTOForAuthorWithBooks)
                            .collect(Collectors.toList())).build();
        }
        return null;
    }
}
