package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.mappers.domain.Author;
import com.javacourse.bookstore.mappers.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTOID;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperForAuthor {

    public Optional<Author> authorReqDTOToAuthor(AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(authorReqDTO)
                .map(a -> Author.builder()
                        .firstName(a.getFirstName())
                        .lastName(a.getLastName())
                        .build());

    }

    public AuthorRespDTO authorToRespDTOStock(Author author) {
        return Optional.ofNullable(author)
                .map(a -> AuthorRespDTO.builder()
                        .firstName(a.getFirstName())
                        .lastName(a.getLastName())
                        .id(a.getId())
                        .build()).orElse(null);

    }

    public AuthorRespDTOID authorRespDTOID(Author author) {
        return Optional.ofNullable(author)
                .map(a -> AuthorRespDTOID
                        .builder()
                        .id(a.getId())
                        .build())
                .orElse(null);
    }
}
