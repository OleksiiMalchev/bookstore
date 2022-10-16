package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.User;
import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MapperUserTest {
    private MapperUser mapperUser = new MapperUser();

    @Test
    void userReqDTOToUser() {
        UserReqDTO userReqDTO = new UserReqDTO("Alex","Ramwtainov","ar@gmail.com","Al",20);
        User userTest = mapperUser.userReqDTOToUser(userReqDTO);
        Assertions.assertEquals(userTest.getFirstName(), userReqDTO.getFirstName());
        Assertions.assertEquals(userTest.getLastName(), userReqDTO.getLastName());
        Assertions.assertEquals(userTest.getDateOfBirth(), userReqDTO.getDateOfBirth());
        Assertions.assertNotNull(userTest);

    }

    @Test
    void toUserRespDTO() {
        User user = new User("Alex","Ramwtainov","ar@gmail.com","Al");
        UserRespDTO userRespDTO = mapperUser.toUserRespDTO(user);
        Assertions.assertNotNull(userRespDTO);
        Assertions.assertEquals(user.getFirstName(), userRespDTO.getFirstName());
        Assertions.assertEquals(user.getEmail(), userRespDTO.getEmail());
        Assertions.assertEquals(user.getNickName(),userRespDTO.getNickName());
    }
}