package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query("SELECT author from Author author join  author.books b  where b.id = :id")
    Optional<Author> findAuthorByBook(@Param("id") Long idBook);
}
