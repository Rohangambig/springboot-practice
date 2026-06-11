package com.entire.demo.controller;

import com.entire.demo.lib.apiResponse;
import com.entire.demo.model.userModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entire.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class user {
    @Autowired
    private UserService user_service;;

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addUser(@RequestBody userModel user) {
        try {
            return user_service.addUser(user);
        }
        catch (Exception e) {

            apiResponse response = new apiResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
