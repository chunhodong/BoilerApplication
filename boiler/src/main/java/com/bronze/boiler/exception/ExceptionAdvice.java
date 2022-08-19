package com.bronze.boiler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler({MemberException.class,ProductStockException.class})
    public ResponseEntity<Map<String,Object>> handler(BaseException exception){
        Map<String,Object> response = Map.of("code",exception.getType(),"message",exception.getType().getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handler(MethodArgumentNotValidException exception){

        String message = exception.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage();
        Map<String,Object> response = Map.of("code",CommonErrorCode.INVALID_PARAM,"message",message);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> handler(HttpMessageNotReadableException exception){
        Map<String,Object> response = Map.of("code",CommonErrorCode.INVALID_PARAM,"message","입력값이 잘못되었습니다");
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }



}
