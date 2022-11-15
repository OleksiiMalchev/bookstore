package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.mappers.MapperAuthorToRespDTO;
import com.javacourse.bookstore.mappers.MapperForAuthor;
import com.javacourse.bookstore.repositories.AuthorRepositories;
import com.javacourse.bookstore.repositories.AuthorRepository;
import exception.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositories authorRepositories;
    private final MapperForAuthor mapperForAuthor;
    private final MapperAuthorToRespDTO mapperAuthorToRespDTO;
    private final AuthorRepository authorRepository;





    @Override
    public List<AuthorRespDTO> getAllAuthor() throws SQLException {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(mapperForAuthor::authorToRespDTOStock)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorRespDTO getAuthorByID(Long ID) throws AuthorNotFoundException {
        return authorRepository.findById(ID)
                .map(mapperForAuthor::authorToRespDTOStock)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found"));

    }

    @Override
    public AuthorRespDTO createAuthor(AuthorReqDTO authorReqDTO) {
        Author author = mapperForAuthor.authorReqDTOToAuthor(authorReqDTO);
        Author save = authorRepository.save(author);
        return mapperForAuthor.authorToRespDTOStock(save);
//        return Optional.ofNullable(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
//                .map(authorRepository::save)
//                .map(mapperForAuthor::authorToRespDTOStock)
//                .orElse(null);


//        return Optional.ofNullable(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
//                .map(authorRepositories::saveAuthorInBase)
//                .map(mapperForAuthor::authorToRespDTOStock)
//                .orElse(null);
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
