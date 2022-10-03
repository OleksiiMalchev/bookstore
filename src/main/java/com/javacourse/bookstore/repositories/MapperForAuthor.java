package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperForAuthor {

    public AuthorRespDTO authorToRespDTO(Author author) {
        return Optional.ofNullable(author)
                .map(a -> new AuthorRespDTO(a.getFirstName(),
                        a.getLastName(),
                        a.getDateOfBirth(),
                        a.getDateOfDeath(),
                        a.getCountryOfBirth(),
                        a.getID()))
                .orElse(null);
    }

    public Author authorReqDTOToAuthor(AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(authorReqDTO)
                .map(a -> new Author(a.getFirstName(),
                        a.getLastName(),
                        a.getSurName(),
                        a.getDateOfBirth(),
                        a.getDateOfDeath(),
                        a.getBiography(),
                        a.getCountryOfBirth(),
                        a.getID()))
                .orElse(null);
    }
}
