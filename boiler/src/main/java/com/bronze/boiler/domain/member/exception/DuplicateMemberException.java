package com.bronze.boiler.domain.member.exception;

import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import org.springframework.http.HttpStatus;

public class DuplicateMemberException extends RuntimeException{

    private MemberExceptionType type;

    public DuplicateMemberException(MemberExceptionType type){
        this.type = type;
    }

    public HttpStatus getHttpStatus(){
        return this.type.getStatus();
    }

    public MemberExceptionType getType() {
        return this.type;
    }
}
