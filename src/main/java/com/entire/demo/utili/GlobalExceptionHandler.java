package com.entire.demo.utili;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleInternalServerError(Exception ex) {
        Response res = new Response(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
        );

        return new ResponseEntity<>(
          res,
          HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handleBadRequestException(BadRequestException ex) {
        Response res = new Response(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                null
        );

        return new ResponseEntity<>(
                res,
                HttpStatus.BAD_REQUEST
        );
    }

//this exception for validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return new ResponseEntity<>(
                new Response(
                        message,
                        HttpStatus.BAD_REQUEST,
                        null
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response> handleUsernameNotFound(UsernameNotFoundException ex) {
        Response res = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                null
        );

        return new ResponseEntity<>(
                res,
                HttpStatus.NOT_FOUND
        );
    }
}
