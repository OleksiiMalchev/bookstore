package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<AuthorRespDTO> getAllAuthor();

    AuthorRespDTO getAuthorByID(Long ID);

    AuthorRespDTO createAuthor(AuthorReqDTO authorReqDTO);

    AuthorRespDTO updateAuthor(Long ID, AuthorReqDTO authorReqDTO);

    AuthorRespDTO deleteAuthor(Long ID);

}
