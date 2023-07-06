package com.byandrev.lnks.controllers;

import com.byandrev.lnks.dto.UserDTO;
import com.byandrev.lnks.entities.Response;
import com.byandrev.lnks.entities.UserEntity;
import com.byandrev.lnks.entities.UserResponse;
import com.byandrev.lnks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/safe")
    public String safe() {
        return "Safe";
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody UserDTO userDTO) {
        UserEntity user = UserEntity
                .builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        if (userService.getUserByEmail(user.getEmail()) != null) {
            Response httpResponse = Response.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .msg("Email already exist").build();
            return new ResponseEntity<>(httpResponse, HttpStatus.BAD_REQUEST);
        }

        userService.createUser(user);

        UserResponse userResponse = UserResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();

        Response httpResponse = Response.builder()
                .status(HttpStatus.CREATED.value())
                .msg("User created")
                .body(userResponse).build();
        return new ResponseEntity<>(httpResponse, HttpStatus.CREATED);
    }

}
