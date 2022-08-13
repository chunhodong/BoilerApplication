package com.bronze.boiler.exception;

public class ProductReviewException extends RuntimeException implements BaseException {

    private ExceptionType type;

    public ProductReviewException(ExceptionType type){
        this.type = type;
    }


    @Override
    public ExceptionType getType() {
        return this.type;
    }
}
