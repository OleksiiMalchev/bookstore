package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
//    Optional<AuthorEntity> findAuthorByFirstName(String authorName);
//    @Query("SELECT a FROM author a where a.firstName = :name")
//    AuthorEntity findAuthor(@Param("name") String authorName);



}
