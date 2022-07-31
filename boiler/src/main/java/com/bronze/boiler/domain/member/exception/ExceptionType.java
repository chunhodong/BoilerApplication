package com.bronze.boiler.domain.member.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionType {

    String getMessage();
    HttpStatus getStatus();


}
