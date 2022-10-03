package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Author;
import com.javacourse.bookstore.domain.dto.AuthorReqDTO;
import com.javacourse.bookstore.domain.dto.AuthorRespDTO;
import com.javacourse.bookstore.repositories.AuthorRepositories;
import com.javacourse.bookstore.repositories.MapperForAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Author authorByID = authorRepositories.getAuthorByID(ID);
        if (authorByID != null) {
            return mapperForAuthor.authorToRespDTO(authorByID);
        }
        return null;
    }

    @Override
    public AuthorRespDTO createAuthor(AuthorReqDTO authorReqDTO) {
        if (authorReqDTO != null) {
            Author newAuthor = mapperForAuthor.authorReqDTOToAuthor(authorReqDTO);
            Author saveAuthor = authorRepositories.saveAuthorInBase(newAuthor);
            AuthorRespDTO authorRespDTO = mapperForAuthor.authorToRespDTO(saveAuthor);
            return authorRespDTO;
        }
        return null;
    }

    @Override
    public AuthorRespDTO updateAuthor(Long ID, AuthorReqDTO authorReqDTO) {
        if (authorReqDTO != null) {
            Author forUpdateAuthor = mapperForAuthor.authorReqDTOToAuthor(authorReqDTO);
            Author updateAuthor = authorRepositories.updateAuthorByID(ID, forUpdateAuthor);
            return mapperForAuthor.authorToRespDTO(updateAuthor);
        }
        return null;
    }

    @Override
    public AuthorRespDTO deleteAuthor(Long ID) {
        Author deleteAuthor = authorRepositories.getAuthorByID(ID);
        if (deleteAuthor != null) {
            authorRepositories.deleteAuthorByID(deleteAuthor.getID());
            return mapperForAuthor.authorToRespDTO(deleteAuthor);
        }
        return null;
    }
}
