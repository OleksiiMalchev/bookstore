package com.javacourse.bookstore.services;

import com.javacourse.bookstore.mappers.MapperAuthorToRespDTO;
import com.javacourse.bookstore.mappers.MapperForAuthor;
import com.javacourse.bookstore.mappers.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.mappers.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final MapperForAuthor mapperForAuthor;
    private final MapperAuthorToRespDTO mapperAuthorToRespDTO;
    private final AuthorRepository authorRepository;


    @Override
    public List<AuthorRespDTO> getAllAuthor() {
        return authorRepository.findAll()
                .stream()
                .map(mapperForAuthor::authorToRespDTO)
                .toList();
    }

    @Override
    public Optional<AuthorRespDTO> getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(mapperForAuthor::authorToRespDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Optional<AuthorRespDTO> createAuthor(AuthorReqDTO authorReqDTO) {
        return mapperForAuthor.authorReqDTOToAuthor(authorReqDTO)
                .map(authorRepository::save)
                .map(mapperForAuthor::authorToRespDTO);
    }

    @Transactional
    @Override
    public Optional<AuthorRespDTO> updateAuthor(Long authorId, AuthorReqDTO authorReqDTO) {
        return authorRepository.findById(authorId)
                .map(a -> {
                    if (authorReqDTO != null) {
                        a.setFirstName(authorReqDTO.getFirstName());
                        a.setLastName(authorReqDTO.getLastName());
                        return a;
                    }
                    return null;
                })
                .map(mapperForAuthor::authorToRespDTO);
    }

    @Override
    public Optional<AuthorRespDTO> deleteAuthor(Long authorId) {
        Optional<AuthorRespDTO> authorRespDTO = authorRepository.findById(authorId)
                .map(mapperForAuthor::authorToRespDTO);
        if (authorRespDTO.isPresent()) {
            authorRepository.deleteById(authorId);
        }
        return authorRespDTO;
    }

    @Override
    public Optional<AuthorRespDTO> findAuthorByBook(Long idBook) {
        return authorRepository.findAuthorByBook(idBook)
                .map(mapperForAuthor::authorToRespDTO);
    }

    @Override
    public Optional<AuthorRespDTOWithBooks> getAuthorWithDetails(Long idAuthor) {
        return authorRepository.findByIdWithBook(idAuthor)
                .map(mapperAuthorToRespDTO::authorToRespDTO);
    }
}
