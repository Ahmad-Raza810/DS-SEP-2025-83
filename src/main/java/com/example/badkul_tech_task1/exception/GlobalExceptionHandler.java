package com.example.badkul_tech_task1.exception;


import com.example.badkul_tech_task1.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    //handler for validation exceptions
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception){

        Map<String,String> errors=new HashMap<>();


        //extracting all validation exception in a map
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        ));

        //preparing error response
        ErrorResponse response=new ErrorResponse(
                "validation failed.",
                errors,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

}
