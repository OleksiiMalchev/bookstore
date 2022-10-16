package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import com.javacourse.bookstore.mappers.MapperUser;
import com.javacourse.bookstore.repositories.UserRepositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepositories userRepositories;
    private final MapperUser mapperUser;

    public UserServiceImpl(UserRepositories userRepositories, MapperUser mapperUser) {
        this.userRepositories = userRepositories;
        this.mapperUser = mapperUser;
    }


    @Override
    public List<UserRespDTO> getAllUser() {
        return userRepositories.getAllAuthor()
                .stream()
                .map(mapperUser::toUserRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserRespDTO getUserByID(Long IDUser) {
        return userRepositories.getUserByID(IDUser)
                .map(mapperUser::toUserRespDTO)
                .orElse(null);
    }

    @Override
    public UserRespDTO createUser(UserReqDTO userReqDTO) {
        return Optional.ofNullable(mapperUser.userReqDTOToUser(userReqDTO))
                .map(userRepositories::saveUserInBase)
                .map(mapperUser::toUserRespDTO)
                .orElse(null);
    }

    @Override
    public UserRespDTO updateUser(Long IDUser, UserReqDTO userReqDTO) {
        return Optional.ofNullable(mapperUser.userReqDTOToUser(userReqDTO))
                .map(u -> userRepositories.updateUserByID(IDUser, u))
                .map(mapperUser::toUserRespDTO)
                .orElse(null);
    }

    @Override
    public UserRespDTO deleteUser(Long IDUser) {
        return userRepositories.deleteUserByID(IDUser)
                .map(mapperUser::toUserRespDTO)
                .orElse(null);
    }
}
