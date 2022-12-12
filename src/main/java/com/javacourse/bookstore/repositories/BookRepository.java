package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query( "SELECT book FROM Book book JOIN book.author a WHERE a.id = :id")
    List<Book> findAllByAuthorId(@Param("id") Long idAuthor);
    List<Book> findAll();
}
