package com.bronze.boiler.exception;

public class ProductException extends RuntimeException implements BaseException {

    private ExceptionType type;

    public ProductException(ExceptionType type){
        this.type = type;
    }


    @Override
    public ExceptionType getType() {
        return this.type;
    }
}
