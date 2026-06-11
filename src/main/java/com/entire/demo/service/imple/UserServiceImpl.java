package com.entire.demo.service.imple;

import com.entire.demo.lib.PasswordConfig;
import com.entire.demo.lib.apiResponse;
import com.entire.demo.model.userModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.entire.demo.repository.userRepo;
import com.entire.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private userRepo user_repo;

    @Autowired
    private PasswordConfig bCryptPasswordEncoder;

    public ResponseEntity<apiResponse> addUser(userModel user) {
        String hashedPassword = user.getPassword();
        hashedPassword = bCryptPasswordEncoder.bCryptPasswordEncoder().encode(hashedPassword);
        user.setPassword(hashedPassword);
        user_repo.save(user);
        return apiResponse.handleResponse("User added successfully",  HttpStatus.OK,user);
    }


}
