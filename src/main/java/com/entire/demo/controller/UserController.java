package com.entire.demo.controller;

import com.entire.demo.DTO.LoginDTO;
import com.entire.demo.Model.UserModel;
import com.entire.demo.service.userService;
import com.entire.demo.utili.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    userService user_service;

    @PostMapping("register")
    public ResponseEntity<Response> registerUser(@RequestBody UserModel user) {
        try {
            return user_service.registerUser(user);

        }catch(Exception e) {
            Response res = new Response(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

            return new ResponseEntity<>(
                    res,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginDTO user) {
        try {
            return user_service.loginUser(user.getEmail(), user.getPassword());
        } catch(Exception e) {
            Response res = new Response(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

            return new ResponseEntity<>(
                    res,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/all")
    ResponseEntity<Response> getUsers() {
        try {

            return user_service.getUsers();

        }catch(Exception e) {
            Response res = new Response(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

            return new ResponseEntity<>(
                    res,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
