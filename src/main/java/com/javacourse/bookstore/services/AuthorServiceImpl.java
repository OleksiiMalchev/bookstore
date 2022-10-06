package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOStock;
import com.javacourse.bookstore.domain.dto.AuthorRespDTOWithBooks;
import com.javacourse.bookstore.mappers.MapperAuthorToRespDTO;
import com.javacourse.bookstore.mappers.MapperForAuthor;
import com.javacourse.bookstore.repositories.AuthorRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepositories authorRepositories;
    private final MapperForAuthor mapperForAuthor;
    private final MapperAuthorToRespDTO mapperAuthorToRespDTO;
    @Autowired
    public AuthorServiceImpl(AuthorRepositories authorRepositories,
                             MapperForAuthor mapperForAuthor,
                             MapperAuthorToRespDTO mapperAuthorToRespDTO) {
        this.authorRepositories = authorRepositories;
        this.mapperForAuthor = mapperForAuthor;
        this.mapperAuthorToRespDTO = mapperAuthorToRespDTO;
    }


    @Override
    public List<AuthorRespDTOStock> getAllAuthor() {
        return authorRepositories.getAllAuthor()
                .stream()
                .map(mapperForAuthor::authorToRespDTOStock)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorRespDTOStock getAuthorByID(Long ID) {
        return authorRepositories.getAuthorByID(ID)
                .map(a -> mapperForAuthor.authorToRespDTOStock(a))
                .orElse(null);
    }

    @Override
    public AuthorRespDTOStock createAuthor(AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
                .map(a -> authorRepositories.saveAuthorInBase(a))
                .map(a -> mapperForAuthor.authorToRespDTOStock(a))
                .orElse(null);
    }

    @Override
    public AuthorRespDTOStock updateAuthor(Long ID, AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
                .map(a -> authorRepositories.updateAuthorByID(ID, a))
                .map(a -> mapperForAuthor.authorToRespDTOStock(a))
                .orElse(null);
    }

    @Override
    public AuthorRespDTOStock deleteAuthor(Long authorID) {
        return authorRepositories.deleteAuthorByID(authorID)
                .map(mapperForAuthor::authorToRespDTOStock)
                .orElse(null);

    }

    public AuthorRespDTOStock findAuthorByBook(Long ID){
        return Optional.ofNullable(authorRepositories.findAuthorByBook(ID))
                .map(a->mapperForAuthor.authorToRespDTOStock(a)).orElse(null);
    }

    public AuthorRespDTOWithBooks getAuthorWithDetails (Long authorID){
        return authorRepositories.getAuthorByID(authorID)
                .map(mapperAuthorToRespDTO::authorToRespDTO)
                .orElse(null);
    }
}
