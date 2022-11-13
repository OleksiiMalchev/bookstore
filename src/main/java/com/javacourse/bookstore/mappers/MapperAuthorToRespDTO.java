package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
                        .dateOfBirth(a.getDateOfBirth())
                        .id(a.getId())
//                        .books(a.getBooks()
//                                .stream()
//                                .map(mapperForBook::toBookRespDTO)
//                                .collect(Collectors.toList()))
                        .build())
                .orElse(null);
    }
}
