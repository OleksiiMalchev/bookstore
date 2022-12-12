package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {
    List<AuthorRespDTO> getAllAuthor() throws SQLException;

    Optional<AuthorRespDTO> getAuthorById(Long id);

    Optional<AuthorRespDTO>  createAuthor(AuthorReqDTO authorReqDTO);

    Optional<AuthorRespDTO> updateAuthor(Long id, AuthorReqDTO authorReqDTO);

    Optional<AuthorRespDTO> deleteAuthor(Long id);

    Optional<AuthorRespDTO> findAuthorByBook(Long id);

    Optional<AuthorRespDTOWithBooks> getAuthorWithDetails(Long authorId);
}
