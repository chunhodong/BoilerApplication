package com.bronze.boiler.domain.member.enums;

import org.springframework.http.HttpStatus;

/**
 * 회원예외타입상수
 */
public enum MemberExceptionType {
    DUPLICATE_NAME("이미 존재하는 이름입니다", HttpStatus.BAD_REQUEST),
    DUPLICATE_EMAIL("이미 존재하는 이메일입니다", HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus status;

    MemberExceptionType(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getStatus(){
        return this.status;
    }

}
