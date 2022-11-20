package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.mappers.MapperAuthorToRespDTO;
import com.javacourse.bookstore.mappers.MapperForAuthor;
import com.javacourse.bookstore.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final MapperForAuthor mapperForAuthor;
    private final MapperAuthorToRespDTO mapperAuthorToRespDTO;
    private final AuthorRepository authorRepository;


    @Override
    public List<AuthorRespDTO> getAllAuthor() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(mapperForAuthor::authorToRespDTOStock)
                .toList();
    }

    @Override
    public Optional<AuthorRespDTO> getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(mapperForAuthor::authorToRespDTOStock);
    }

    @Override
    public Optional<AuthorRespDTO> createAuthor(AuthorReqDTO authorReqDTO) {
        return mapperForAuthor.authorReqDTOToAuthor(authorReqDTO)
                .map(authorRepository::save)
                .map(mapperForAuthor::authorToRespDTOStock);
    }

    @Override
    public Optional<AuthorRespDTO> updateAuthor(Long authorId, AuthorReqDTO authorReqDTO) {
        return authorRepository.findById(authorId)
                .map(a -> {
                    if (authorReqDTO != null) {
                        a.setFirstName(authorReqDTO.getFirstName());
                        a.setLastName(authorReqDTO.getLastName());
                        a.setSurName(authorReqDTO.getSurName());
                        a.setBiography(authorReqDTO.getBiography());
                        a.setCountryOfBirth(authorReqDTO.getCountryOfBirth());
                        a.setDateOfDeath(authorReqDTO.getDateOfDeath());
                        a.setDateOfBirth(authorReqDTO.getDateOfBirth());
                        return a;
                    }
                    return null;
                })
                .map(mapperForAuthor::authorToRespDTOStock);
    }

    @Override
    public Optional<AuthorRespDTO> deleteAuthor(Long authorId) {
        Optional<AuthorRespDTO> authorRespDTO = authorRepository.findById(authorId)
                .map(mapperForAuthor::authorToRespDTOStock);
        if (authorRespDTO.isPresent()) {
            authorRepository.deleteById(authorId);
        }
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
