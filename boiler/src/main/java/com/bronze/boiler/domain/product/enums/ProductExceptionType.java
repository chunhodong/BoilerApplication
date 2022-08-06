package com.bronze.boiler.domain.product.enums;

import com.bronze.boiler.exception.ExceptionType;
import org.springframework.http.HttpStatus;

/**
 * 회원예외타입상수
 */
public enum ProductExceptionType implements ExceptionType {
    NONE_EXIST_PRODUCT("존재하지않는 상품입니다",HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus status;

    ProductExceptionType(String message, HttpStatus status) {
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
