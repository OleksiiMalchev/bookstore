package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query("SELECT author from Author author join  author.books b  where b.id = :id")
    Optional<Author> findAuthorByBook(@Param("id") Long idBook);

    @Override
    List<Author> findAll();

    @Query("SELECT author from Author author left join fetch  author.books where author.id = :id")
    Optional<Author> findByIdWithBook(@Param("id") Long idAuthor);

}
