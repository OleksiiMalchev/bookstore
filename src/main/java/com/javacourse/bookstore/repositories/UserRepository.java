package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.mappers.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
