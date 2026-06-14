package com.entire.demo.service.imple;

import com.entire.demo.dto.LoginRequestDTO;
import com.entire.demo.dto.UserDTO;
import com.entire.demo.lib.JwtImpl;
import com.entire.demo.lib.PasswordConfig;
import com.entire.demo.lib.apiResponse;
import com.entire.demo.model.userModel;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.entire.demo.repository.userRepo;
import com.entire.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseEntity<apiResponse> getAllUsers() {
        List<UserDTO> data = user_repo.findAll()
                .stream().map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());

        return apiResponse.handleResponse("users data fetched successfully",HttpStatus.OK,data);
    }

    public ResponseEntity<apiResponse> login(LoginRequestDTO user) {
        userModel getUser = user_repo.findByEmail(user.getEmail());
        if(getUser == null)
            return apiResponse.handleResponse("User not found",HttpStatus.NOT_FOUND,user);

        boolean isPasswordMatch = bCryptPasswordEncoder.bCryptPasswordEncoder()
                .matches(
                        user.getPassword(),
                        getUser.getPassword()
                );

        if(!isPasswordMatch)
            return apiResponse.handleResponse("Wrong password",HttpStatus.UNAUTHORIZED,user);

        String JWT_TOKEN = JwtImpl.generateToken(getUser);
        return apiResponse.handleResponse("login successful",HttpStatus.OK,JWT_TOKEN);
    }

    public ResponseEntity<apiResponse> tokenValidation(String token) {
        Claims data = JwtImpl.validateToken(token);
        return apiResponse.handleResponse("token validated",HttpStatus.OK,data);
    }

}
