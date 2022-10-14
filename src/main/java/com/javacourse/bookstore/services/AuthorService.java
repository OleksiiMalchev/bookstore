package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOStock;
import exception.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<AuthorRespDTOStock> getAllAuthor();

    AuthorRespDTOStock getAuthorByID(Long ID) throws AuthorNotFoundException;

    AuthorRespDTOStock createAuthor(AuthorReqDTO authorReqDTO);

    AuthorRespDTOStock updateAuthor(Long ID, AuthorReqDTO authorReqDTO);

    AuthorRespDTOStock deleteAuthor(Long ID);

}
