package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookRepositories {

    private final Map<Long, Book> baseBook = new HashMap<>();
    private final Random randomID = new Random();

    public List<Book> findAll() {
        return baseBook.values()
                .stream()
                .collect(Collectors.toList());

    }

    public List<Book> findAllByAuthorID(Long authorID) {
        return baseBook.values()
                .stream().filter(b->b.getAuthorID().equals(authorID))
                .collect(Collectors.toList());
    }

    public Optional<Book> findById(Long idBook) {
        return Optional.ofNullable(baseBook.get(idBook));
    }

    public Book save(Book book) {
        book.setId(randomID.nextLong());
        book.setESBI(randomID.nextLong());
        baseBook.put(book.getId(), book);
        return book;
    }

    public Book update(Long id, Book book) {
        if(id!=null && book!=null){
            baseBook.put(id,book);
            return book;
        }
       return null;
    }

    public Optional<Book> remove(Long id) {
        return Optional.ofNullable(baseBook.remove(id));
    }
}
