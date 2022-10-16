package com.javacourse.bookstore.repositories;


import com.javacourse.bookstore.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
@Repository
public class UserRepositories {
    private final Map<Long, User> baseUser = new HashMap<>();
    private final Random randomID = new Random();


    public List<User> getAllAuthor() {
        return baseUser.values()
                .stream()
                .collect(Collectors.toList());
    }

    public Optional<User> getUserByID(Long ID) {
        return Optional.ofNullable(baseUser.get(ID));

    }

    public User saveUserInBase(User user) {
        if (user != null) {
            user.setIDUser(randomID.nextLong());
            baseUser.put(user.getIDUser(), user);
            return user;
        }
        return null;
    }

    public User updateUserByID(Long IDUser, User user) {
        if (baseUser.containsKey(IDUser)) {
            user.setIDUser(IDUser);
            baseUser.put(user.getIDUser(), user);
            return baseUser.get(user.getIDUser());
        }
        return null;
    }

    public Optional<User> deleteUserByID(Long IDUser) {
        return Optional.ofNullable(baseUser.remove(IDUser));
    }

}
