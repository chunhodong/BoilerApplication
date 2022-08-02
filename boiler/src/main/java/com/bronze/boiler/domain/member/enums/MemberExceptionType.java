package com.bronze.boiler.domain.member.enums;

import com.bronze.boiler.domain.member.exception.ExceptionType;
import org.springframework.http.HttpStatus;

/**
 * 회원예외타입상수
 */
public enum MemberExceptionType implements ExceptionType {
    DUPLICATE_NAME("이미 존재하는 이름입니다", HttpStatus.BAD_REQUEST),
    DUPLICATE_EMAIL("이미 존재하는 이메일입니다", HttpStatus.BAD_REQUEST),
    NONE_EXIST("존재하지않는 회원입니다",HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus status;

    MemberExceptionType(String message, HttpStatus status) {
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
