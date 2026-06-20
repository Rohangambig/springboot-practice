package com.entire.demo.utili;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String message;
    private HttpStatus status;
    private Object data;

    public static ResponseEntity<Response> handleResponse(
            String message,
            HttpStatus status,
            Object data
    ) {
        Response res = new Response(message,status,data);
        return new ResponseEntity<>(
                res,
                status
        );
    }

}
