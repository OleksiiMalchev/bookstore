package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOID;
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

    public AuthorRespDTO authorToRespDTO(Author author) {
        if(author!=null){
            return AuthorRespDTO.builder()
                    .authorId(author.getId())
                    .firstName(author.getFirstName())
                    .lastName(author.getLastName())
                    .build();
        }
        return null;
    }

    public AuthorRespDTOID authorRespDTOID(Author author) {
        if(author!=null){
            return AuthorRespDTOID
                    .builder()
                    .id(author.getId())
                    .build();
        }
        return null;
    }
}
