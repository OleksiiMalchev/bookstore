package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Author;

import com.javacourse.bookstore.domain.dto.AuthorDto;
import com.javacourse.bookstore.repositories.AuthorRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositories authorRepositories;

    @Autowired
    public AuthorServiceImpl(AuthorRepositories authorRepositories) {
        this.authorRepositories = authorRepositories;
    }

    public List<AuthorDto> allAuthor() {
        List<Author> getAllAuthors = authorRepositories.findAll();
        List<AuthorDto> authorDto = new ArrayList<>();
        for (Author authorFor : getAllAuthors) {
            authorDto.add(new AuthorDto(authorFor.getFirstName(), authorFor.getLastName(), authorFor.getId()));
        }
        return authorDto;
    }

    public AuthorDto getAuthorById(long id) {
        Author author = authorRepositories.findById(id);
        return new AuthorDto(author.getFirstName(), author.getLastName(), author.getId());
    }

    public AuthorDto create(Author author) {
        Author authorSave = authorRepositories.save(author);
        return new AuthorDto(author.getFirstName(), author.getLastName(), author.getId());
    }

    public AuthorDto update(long id, Author author) {
        Author authorUpdate = authorRepositories.update(id, author);
        return new AuthorDto(authorUpdate.getFirstName(), authorUpdate.getLastName(), authorUpdate.getId());
    }

    public AuthorDto delete(long id) {
        Author authorDelete = authorRepositories.remove(id);
        return new AuthorDto(authorDelete.getFirstName(), authorDelete.getFirstName(), authorDelete.getId());
    }
}
