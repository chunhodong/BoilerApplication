package com.bronze.boiler.exception;

import org.springframework.http.HttpStatus;

/**
 * 예외타입 인터페이스
 * 추상화하면서 핸들링 로직을 단순화
 */
public interface ExceptionType {

    String getMessage();
    HttpStatus getStatus();
}
