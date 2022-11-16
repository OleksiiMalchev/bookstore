package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.mappers.MapperAuthorToRespDTO;
import com.javacourse.bookstore.mappers.MapperForAuthor;
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
    public AuthorRespDTO getAuthorById(Long id) throws AuthorNotFoundException {
        return authorRepository.findById(id)
                .map(mapperForAuthor::authorToRespDTOStock)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found"));
    }

    @Override
    public AuthorRespDTO createAuthor(AuthorReqDTO authorReqDTO) {
        Author author = mapperForAuthor.authorReqDTOToAuthor(authorReqDTO);
        Author save = authorRepository.save(author);
        return mapperForAuthor.authorToRespDTOStock(save);
    }

    @Override
    public AuthorRespDTO updateAuthor(Long authorId, AuthorReqDTO authorReqDTO) {
        Author authorInDataBase = authorRepository.findById(authorId).get();
        Author authorInReq = mapperForAuthor.authorReqDTOToAuthor(authorReqDTO);
        authorInDataBase.setFirstName(authorInReq.getFirstName());
        authorInDataBase.setLastName(authorInReq.getLastName());
        authorInDataBase.setSurName(authorInReq.getSurName());
        authorInDataBase.setBiography(authorInReq.getBiography());
        authorInDataBase.setCountryOfBirth(authorInReq.getCountryOfBirth());
        authorInDataBase.setDateOfBirth(authorInReq.getDateOfBirth());
        authorInDataBase.setDateOfDeath(authorInReq.getDateOfBirth());
        Author saveAuthor = authorRepository.save(authorInDataBase);
        return mapperForAuthor.authorToRespDTOStock(saveAuthor);
    }

    @Override
    public AuthorRespDTO deleteAuthor(Long authorId) {
        Author authorInBase = authorRepository.findById(authorId).get();
        AuthorRespDTO authorRespDTO = mapperForAuthor.authorToRespDTOStock(authorInBase);
        authorRepository.delete(authorInBase);
        return authorRespDTO;
    }

    @Override
    public Optional<AuthorRespDTO> findAuthorByBook(Long idBook) {
        return authorRepository.findAuthorByBook(idBook)
                .map(mapperForAuthor::authorToRespDTOStock);
    }

    @Override
    public Optional<AuthorRespDTOWithBooks> getAuthorWithDetails(Long idAuthor) {
        return authorRepository.findById(idAuthor)
                .map(mapperAuthorToRespDTO::authorToRespDTO);

    }
}
