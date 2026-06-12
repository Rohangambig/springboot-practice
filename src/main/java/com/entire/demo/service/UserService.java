package com.entire.demo.service;

import com.entire.demo.lib.apiResponse;
import com.entire.demo.model.userModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    ResponseEntity<apiResponse> addUser(@RequestBody userModel user);
    ResponseEntity<apiResponse> getAllUsers();
}
