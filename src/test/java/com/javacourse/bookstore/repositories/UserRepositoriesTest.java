package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoriesTest {
    @Autowired
    private UserRepositories userRepositories;

    @Test
    void getAllUser() {
        User user = User.builder().firstName("Alex1").lastName("Ramwtainov1").email("ar1@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        User user1 = User.builder().firstName("Alex2").lastName("Ramwtainov2").email("ar2@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        User user2 = User.builder().firstName("Alex3").lastName("Ramwtainov3").email("ar3@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        userRepositories.saveUserInBase(user);
        userRepositories.saveUserInBase(user1);
        userRepositories.saveUserInBase(user2);
        List<User> allUser = userRepositories.getAllUser();
        Optional<User> optionalUser = allUser.stream()
                .filter(b -> b.getFirstName().equals(user.getFirstName()))
                .findAny();
        Assertions.assertEquals(optionalUser.get().getFirstName(), user.getFirstName());
        Optional<User> optionalUser1 = allUser.stream()
                .filter(b -> b.getFirstName().equals(user1.getFirstName()))
                .findAny();
        Assertions.assertEquals(optionalUser1.get().getFirstName(), user1.getFirstName());
        Optional<User> optionalUser2 = allUser.stream()
                .filter(b -> b.getFirstName().equals(user2.getFirstName()))
                .findAny();
        Assertions.assertEquals(optionalUser2.get().getFirstName(), user2.getFirstName());
    }

    @Test
    void getUserByID() {
        User user = User.builder().firstName("Alex1").lastName("Ramwtainov1").email("ar1@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        User saveUserInBase = userRepositories.saveUserInBase(user);
        Assertions.assertEquals(userRepositories.getUserByID(saveUserInBase.getId()).get(), user);
    }

    @Test
    void saveUserInBase() {
        User user = User.builder().firstName("Alex1").lastName("Ramwtainov1").email("ar1@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        User saveUserInBase = userRepositories.saveUserInBase(user);
        Assertions.assertEquals(user, saveUserInBase);
        User saveUserInBase1 = userRepositories.saveUserInBase(null);
        Assertions.assertNull(saveUserInBase1);
    }

    @Test
    void updateUserByID() {
        User userForAdd = User.builder().firstName("Alex1").lastName("Ramwtainov1").email("ar1@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        User userForUpdate = User.builder().firstName("Alex2").lastName("Ramwtainov2").email("ar2@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        userRepositories.saveUserInBase(userForAdd);
        User updateUserByID = userRepositories.updateUserByID(userForAdd.getId(), userForUpdate);
        Assertions.assertEquals(updateUserByID, userForUpdate);
    }

    @Test
    void updateUserByIDIfNoIDInBase() {
        User userForAdd = User.builder().firstName("Alex1").lastName("Ramwtainov1").email("ar1@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        User userForUpdate = User.builder().firstName("Alex2").lastName("Ramwtainov2").email("ar2@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        User updateUserByID = userRepositories.updateUserByID(userForAdd.getId(), userForUpdate);
        Assertions.assertNull(updateUserByID);
    }

    @Test
    void deleteUserByID() {
        User userForAdd = User.builder().firstName("Alex1").lastName("Ramwtainov1").email("ar1@gmail.com")
                .dateOfBirth(LocalDate.of(1881, 05, 20)).build();
        User saveUserInBase = userRepositories.saveUserInBase(userForAdd);
        Optional<User> deleteUserByID = userRepositories.deleteUserByID(saveUserInBase.getId());
        Assertions.assertEquals(deleteUserByID.get(), userForAdd);
    }
}