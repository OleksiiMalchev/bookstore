package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRealRepository extends CrudRepository<AuthorEntity, Long> {


}
