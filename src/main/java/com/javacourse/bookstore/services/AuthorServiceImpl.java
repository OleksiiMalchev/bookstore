package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.mappers.MapperAuthorToRespDTO;
import com.javacourse.bookstore.mappers.MapperForAuthor;
import com.javacourse.bookstore.repositories.AuthorRepositories;
import exception.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositories authorRepositories;
    private final MapperForAuthor mapperForAuthor;
    private final MapperAuthorToRespDTO mapperAuthorToRespDTO;




    @Override
    public List<AuthorRespDTO> getAllAuthor() throws SQLException {
        return authorRepositories.getAllAuthor()
                .stream()
                .map(mapperForAuthor::authorToRespDTOStock)
                .collect(Collectors.toList());

    }

    @Override
    public AuthorRespDTO getAuthorByID(Long ID) throws AuthorNotFoundException {
        return authorRepositories.getAuthorByID(ID)
                .map(mapperForAuthor::authorToRespDTOStock)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found"));
    }

    @Override
    public AuthorRespDTO createAuthor(AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
                .map(authorRepositories::saveAuthorInBase)
                .map(mapperForAuthor::authorToRespDTOStock)
                .orElse(null);
    }

    @Override
    public AuthorRespDTO updateAuthor(Long authorID, AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
                .map(a -> authorRepositories.updateAuthorByID(authorID, a))
                .map(mapperForAuthor::authorToRespDTOStock)
                .orElse(null);
    }

    @Override
    public AuthorRespDTO deleteAuthor(Long authorID) {
        return authorRepositories.deleteAuthorByID(authorID)
                .map(mapperForAuthor::authorToRespDTOStock)
                .orElse(null);
    }

    @Override
    public AuthorRespDTO findAuthorByBook(Long ID) {
        return Optional.ofNullable(authorRepositories.findAuthorByBook(ID))
                .map(mapperForAuthor::authorToRespDTOStock)
                .orElse(null);
    }

    @Override
    public AuthorRespDTOWithBooks getAuthorWithDetails(Long authorID) {
        return authorRepositories.getAuthorByID(authorID)
                .map(mapperAuthorToRespDTO::authorToRespDTO)
                .orElse(null);
    }
}
