package com.bronze.boiler.order.exception;

import com.bronze.boiler.exception.ExceptionType;
import org.springframework.http.HttpStatus;

/**
 * 회원예외타입상수
 */
public enum OrderException implements ExceptionType {
    NONE_EXIST_ORDER("존재하지않는 주문입니다",HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus status;

    OrderException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }




    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }
}
