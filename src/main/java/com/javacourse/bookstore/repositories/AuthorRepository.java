package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findAuthorByFirstName(String authorName);
    @Query("SELECT a FROM author a where a.firstName = :name")
    AuthorEntity findAuthor(@Param("name") String authorName);



}
