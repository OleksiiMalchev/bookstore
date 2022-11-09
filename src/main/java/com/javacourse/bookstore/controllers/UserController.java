package com.javacourse.bookstore.controllers;

import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import com.javacourse.bookstore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<? super UserRespDTO> getUserByID(@PathVariable("id") Long idUser) {
        UserRespDTO userByID = userService.getUserByID(idUser);
        if (userByID != null) {
            return ResponseEntity.ok(userByID);
        }
        return new ResponseEntity<>("User not found ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users")
    public ResponseEntity<? super UserRespDTO> allUsers() {
        List<UserRespDTO> allUsers = userService.getAllUser();
        if (allUsers != null) {
            return ResponseEntity.status(200).body(allUsers);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<? super UserRespDTO> createUser(@RequestBody(required = false) UserReqDTO userReqDTO) {
        if (userReqDTO != null) {
            return ResponseEntity.status(201).body(userService.createUser(userReqDTO));
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<? super UserRespDTO> updateUser(@PathVariable("id") Long idUser,
                                                          @RequestBody(required = false) UserReqDTO userReqDTO) {
        if (userReqDTO != null && idUser != null) {
            return ResponseEntity.status(200).body(userService.updateUser(idUser, userReqDTO));
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<? super UserRespDTO> deleteUser(@PathVariable("id") Long idUser) {
        if (idUser != null) {
            return ResponseEntity.status(200).body(userService.deleteUser(idUser));
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
