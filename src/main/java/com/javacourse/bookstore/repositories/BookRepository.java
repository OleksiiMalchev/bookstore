package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "SELECT * from Book as b inner join author as a on b.author_id= a.id  where a.id = ?",
            nativeQuery = true)
    List<Book> findAllByAuthorID(Long idAuthor);

}
