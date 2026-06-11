package service;

import lib.apiResponse;
import model.userModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<apiResponse> addUser(userModel user);
    ResponseEntity<apiResponse> getAllUsers();
}
