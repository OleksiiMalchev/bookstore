package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.repositories.AuthorRepositories;
import com.javacourse.bookstore.repositories.MapperForAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepositories authorRepositories;
    private final MapperForAuthor mapperForAuthor;

    @Autowired
    public AuthorServiceImpl(AuthorRepositories authorRepositories, MapperForAuthor mapperForAuthor) {
        this.authorRepositories = authorRepositories;
        this.mapperForAuthor = mapperForAuthor;
    }


    @Override
    public List<AuthorRespDTO> getAllAuthor() {
        return authorRepositories.getAllAuthor()
                .stream()
                .map(mapperForAuthor::authorToRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorRespDTO getAuthorByID(Long ID) {
        return Optional.ofNullable(authorRepositories.getAuthorByID(ID))
                .map(a -> mapperForAuthor.authorToRespDTO(a))
                .orElse(null);
    }

    @Override
    public AuthorRespDTO createAuthor(AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
                .map(a -> authorRepositories.saveAuthorInBase(a))
                .map(a -> mapperForAuthor.authorToRespDTO(a))
                .orElse(null);
    }

    @Override
    public AuthorRespDTO updateAuthor(Long ID, AuthorReqDTO authorReqDTO) {
        return Optional.ofNullable(mapperForAuthor.authorReqDTOToAuthor(authorReqDTO))
                .map(a -> authorRepositories.updateAuthorByID(ID, a))
                .map(a -> mapperForAuthor.authorToRespDTO(a))
                .orElse(null);
    }

    @Override
    public AuthorRespDTO deleteAuthor(Long ID) {
        return Optional.ofNullable(authorRepositories.getAuthorByID(ID))
                .map(a -> authorRepositories.deleteAuthorByID(a.getID()))
                .map(a -> mapperForAuthor.authorToRespDTO(a))
                .orElse(null);
    }

    public AuthorRespDTO findAuthorByBook(Long ID){
        return Optional.ofNullable(authorRepositories.findAuthorByBook(ID))
                .map(a->mapperForAuthor.authorToRespDTO(a)).orElse(null);
    }

}
