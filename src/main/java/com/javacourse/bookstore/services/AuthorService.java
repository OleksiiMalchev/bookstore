package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import exception.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface AuthorService {
    List<AuthorRespDTO> getAllAuthor() throws SQLException;

    AuthorRespDTO getAuthorByID(Long ID) throws AuthorNotFoundException;

    AuthorRespDTO createAuthor(AuthorReqDTO authorReqDTO);

    AuthorRespDTO updateAuthor(Long ID, AuthorReqDTO authorReqDTO);

    AuthorRespDTO deleteAuthor(Long ID);

    AuthorRespDTO findAuthorByBook(Long ID);

    AuthorRespDTOWithBooks getAuthorWithDetails(Long authorID);
}
