package com.entire.demo.lib;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class apiResponse {
    private String message;
    private HttpStatus status;
    private Object data;

    public static ResponseEntity<apiResponse> handleResponse(String message, HttpStatus status, Object data1) {
        apiResponse response = new apiResponse(message, status, data1);
        return new ResponseEntity<>(response, status    );
    }
}
