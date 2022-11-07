package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOID;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperForAuthor {

    public Author authorReqDTOToAuthor(AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(authorReqDTO)
                .map(a -> Author.builder()
                        .firstName(a.getFirstName())
                        .lastName(a.getLastName())
                        .dateOfBirth(a.getDateOfBirth())
                        .build())
                .orElse(null);
    }

    public AuthorRespDTO authorToRespDTOStock(Author author) {
        return Optional.ofNullable(author)
                .map(a -> AuthorRespDTO.builder()
                        .firstName(a.getFirstName())
                        .lastName(a.getLastName())
                        .dateOfBirth(a.getDateOfBirth())
                        .id(a.getID())
                        .build())
                .orElse(null);
    }

    public AuthorRespDTOID authorRespDTOID(Author author) {
        return Optional.ofNullable(author)
                .map(a -> AuthorRespDTOID
                        .builder()
                        .id(a.getID())
                        .build())
                .orElse(null);
    }
}
