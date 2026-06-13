package com.entire.demo.service;

import com.entire.demo.dto.LoginRequestDTO;
import com.entire.demo.lib.apiResponse;
import com.entire.demo.model.userModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    ResponseEntity<apiResponse> addUser(userModel user);
    ResponseEntity<apiResponse> getAllUsers();
    ResponseEntity<apiResponse> login(LoginRequestDTO user);
}
