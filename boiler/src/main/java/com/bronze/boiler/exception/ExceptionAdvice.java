package com.bronze.boiler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<Map<String,Object>> handler(MemberException exception){
        Map<String,Object> response = Map.of("code",exception.getType(),"message",exception.getType().getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}
