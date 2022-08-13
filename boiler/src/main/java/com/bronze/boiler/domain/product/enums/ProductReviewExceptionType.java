package com.bronze.boiler.domain.product.enums;

import com.bronze.boiler.exception.ExceptionType;
import org.springframework.http.HttpStatus;

/**
 * 회원예외타입상수
 */
public enum ProductReviewExceptionType implements ExceptionType {
    NONE_EXIST_REVIEW("존재하지않는 댓글입니다",HttpStatus.BAD_REQUEST),
    REMOVED_REVIEW("삭제된 댓글입니다",HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus status;

    ProductReviewExceptionType(String message, HttpStatus status) {
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
