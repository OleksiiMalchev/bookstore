package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query(value = "SELECT * from Author as a inner join book as b on a.id = b.author_id where b.id = ?",
            nativeQuery = true)
    Optional<Author> findAuthorByBook(Long idBook);

//    @Query("SELECT author FROM Author author INNER JOIN FETCH author.book where book.id = :id")
//    Author findAuthorByBook(@Param("id") Long id);

//    @Query("FROM Author a INNER JOIN  a.book b where b.id = :id")
//    Author findAuthorByBook(@Param("id") Long id);

//    @Query("SELECT author FROM Book a LEFT JOIN FETCH a.book WHERE id = :id")
//    Author getAuthorWithDetails(@Param("id") Long id);

}
