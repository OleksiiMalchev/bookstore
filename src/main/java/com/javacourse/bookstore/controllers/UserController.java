package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import com.javacourse.bookstore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<? super UserRespDTO> getUserByID(@PathVariable("id") Long idUser) {
        Optional<UserRespDTO> userById = userService.getUserById(idUser);
        if (userById.isPresent()) {
            return ResponseEntity.ok(userById);
        }
        return new ResponseEntity<>("User not found ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users")
    public ResponseEntity<? super UserRespDTO> allUsers() {
        List<UserRespDTO> allUsers = userService.getAllUser();
        if (allUsers.isEmpty()) {
            return new ResponseEntity<>("Users not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(allUsers);
    }

    @PostMapping("/user")
    public ResponseEntity<? super UserRespDTO> createUser(@RequestBody(required = false) UserReqDTO userReqDTO) {
        Optional<UserRespDTO> user = userService.createUser(userReqDTO);
        if (user.isPresent()) {
            return ResponseEntity.status(201).body(user);
        }
        return new ResponseEntity<>("Invalid request. User not create", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<? super UserRespDTO> updateUser(@PathVariable("id") Long idUser,
                                                          @RequestBody(required = false) UserReqDTO userReqDTO) {
        Optional<UserRespDTO> userRespDTO = userService.updateUser(idUser, userReqDTO);
        if (userRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(userRespDTO);
        }
        return new ResponseEntity<>("Invalid request. User not update", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<? super UserRespDTO> deleteUser(@PathVariable("id") Long idUser) {
        Optional<UserRespDTO> userRespDTO = userService.deleteUser(idUser);
        if (userRespDTO.isPresent()) {
            return ResponseEntity.status(200).body(userRespDTO);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
