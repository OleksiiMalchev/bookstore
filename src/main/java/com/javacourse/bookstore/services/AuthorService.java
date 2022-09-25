package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.Author;

import com.javacourse.bookstore.domain.dto.AuthorDto;


import java.util.List;

public interface AuthorService {
    List<AuthorDto> allAuthor();  //GET/authors - show all books
    AuthorDto getAuthorById(long id);  //GET/authors/{id}- display an authors by id
    AuthorDto  create(Author author); //POST/authors - create new authors
    AuthorDto  upDate(long id, Author author);//PUT/authors/{id}    - update a author by id
    AuthorDto delete(long id);//DELETE/{id} - delete an author by id
}
