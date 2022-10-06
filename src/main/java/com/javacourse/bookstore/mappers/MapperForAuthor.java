package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOID;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOStock;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperForAuthor {

    public Author authorReqDTOToAuthor(AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(authorReqDTO)
                .map(a -> new Author(a.getFirstName(),
                        a.getLastName(),
                        a.getDateOfBirth()))
                .orElse(null);
    }

    public AuthorRespDTOStock authorToRespDTOStock(Author author) {
        return Optional.ofNullable(author)
                .map(a -> new AuthorRespDTOStock(a.getFirstName(),
                        a.getLastName(),
                        a.getDateOfBirth(),
                        a.getID())).orElse(null);
    }

    public AuthorRespDTOID authorRespDTOID(Author author) {
        return Optional.ofNullable(author)
                .map(a -> new AuthorRespDTOID())
                .stream()
                .peek(a -> a.setID(author.getID()))
                .findAny()
                .orElse(null);
    }
}
