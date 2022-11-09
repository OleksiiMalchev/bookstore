package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<UserRespDTO> getAllUser();

    UserRespDTO getUserByID(Long IDUser);

    UserRespDTO createUser(UserReqDTO userReqDTO);

    UserRespDTO updateUser(Long IDUser, UserReqDTO userReqDTO);

    UserRespDTO deleteUser(Long IDUser);
}
