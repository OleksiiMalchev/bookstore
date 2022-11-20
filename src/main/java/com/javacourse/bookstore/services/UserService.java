package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserRespDTO> getAllUser();

    Optional<UserRespDTO> getUserById(Long userId);

    Optional<UserRespDTO> createUser(UserReqDTO userReqDTO);

    Optional<UserRespDTO> updateUser(Long userId, UserReqDTO userReqDTO);

    Optional<UserRespDTO> deleteUser(Long userId);
}
