package com.entire.demo.service.Implementation;

import com.entire.demo.Model.UserModel;
import com.entire.demo.repo.UserRepository;
import com.entire.demo.service.userService;
import com.entire.demo.utili.JWTUtil;
import com.entire.demo.utili.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements userService {

    @Autowired
    UserRepository user_repository;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Response> registerUser(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        System.out.println("role: "+userModel.getRole());
        user_repository.save(userModel);

        return new ResponseEntity<>(
            new Response(
                    "Registered successfully",
                    HttpStatus.OK,
                    userModel
            ),
            HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Response> loginUser(String email, String password) {

        Optional<UserModel> user = user_repository.findByEmail(email);

        if(user.isEmpty()) {
            return new ResponseEntity<>(
                    new Response(
                            "Incorrect Email/Password",
                            HttpStatus.UNAUTHORIZED,
                            null
                    ),
                    HttpStatus.UNAUTHORIZED
            );
        }
        UserModel getUser = user.get();



        if(!passwordEncoder.matches(password, getUser.getPassword())) {
            return new ResponseEntity<>(
                    new Response(
                            "Incorrect Email/Password",
                            HttpStatus.UNAUTHORIZED,
                            null
                    ),
                    HttpStatus.UNAUTHORIZED
            );
        }

        String token = jwtUtil.generateToken(getUser.getEmail());
        return new ResponseEntity<>(
                new Response(
                        "loggin done successfully",
                        HttpStatus.OK,
                        token
                ),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Response> getUsers(Pageable pageable) {

        Page<UserModel> users = user_repository.findAll(pageable);

        Response res = new Response(
                "fetched all the users",
                HttpStatus.OK,
                users
        );

        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
