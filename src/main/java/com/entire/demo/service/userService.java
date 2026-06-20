package com.entire.demo.service;

import com.entire.demo.Model.UserModel;
import com.entire.demo.utili.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;

public interface  userService {
    ResponseEntity<Response> registerUser(UserModel userModel);
    ResponseEntity<Response> loginUser(String email, String password);
    ResponseEntity<Response> getUsers(Pageable pageable);
}
