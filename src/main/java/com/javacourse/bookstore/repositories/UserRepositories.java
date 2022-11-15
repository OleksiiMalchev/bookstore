package com.javacourse.bookstore.repositories;


import com.javacourse.bookstore.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
@Repository
public class UserRepositories {
    private final Map<Long, User> baseUser = new HashMap<>();
    private final Random randomID = new Random();


    public List<User> getAllUser() {
        return baseUser.values()
                .stream()
                .collect(Collectors.toList());
    }

    public Optional<User> getUserByID(Long ID) {
        return Optional.ofNullable(baseUser.get(ID));

    }

    public User saveUserInBase(User user) {
//        if (user != null) {
//            user.setIid(randomID.nextLong());
//            baseUser.put(user.getIid(), user);
//            return user;
//        }
        return null;
    }

    public User updateUserByID(Long IDUser, User user) {
//        if (baseUser.containsKey(IDUser)) {
//            user.setIid(IDUser);
//            baseUser.put(user.getIid(), user);
//            return baseUser.get(user.getIid());
//        }
        return null;
    }

    public Optional<User> deleteUserByID(Long IDUser) {
        return Optional.ofNullable(baseUser.remove(IDUser));
    }

}
