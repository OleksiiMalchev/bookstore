package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import org.springframework.stereotype.Component;

@Component
public class MapperForAuthor {

    public AuthorRespDTO authorToRespDTO(Author author) {
        if (author != null) {
            return new AuthorRespDTO(author.getFirstName(),
                    author.getLastName(),
                    author.getDateOfBirth(),
                    author.getDateOfDeath(),
                    author.getCountryOfBirth(),
                    author.getID());
        }
        return null;
    }

    public Author authorReqDTOToAuthor(AuthorReqDTO authorReqDTO) {
        if (authorReqDTO != null) {
            return new Author(authorReqDTO.getFirstName(),
                    authorReqDTO.getLastName(),
                    authorReqDTO.getSurName(),
                    authorReqDTO.getDateOfBirth(),
                    authorReqDTO.getDateOfDeath(),
                    authorReqDTO.getBiography(),
                    authorReqDTO.getCountryOfBirth(),
                    authorReqDTO.getID());
        }
        return null;
    }
}
