package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import exception.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {
    List<AuthorRespDTO> getAllAuthor() throws SQLException;

    AuthorRespDTO getAuthorById(Long ID) throws AuthorNotFoundException;

    AuthorRespDTO createAuthor(AuthorReqDTO authorReqDTO);

    AuthorRespDTO updateAuthor(Long ID, AuthorReqDTO authorReqDTO);

    AuthorRespDTO deleteAuthor(Long ID);

    Optional<AuthorRespDTO> findAuthorByBook(Long ID);

    Optional<AuthorRespDTOWithBooks> getAuthorWithDetails(Long authorID);
}
