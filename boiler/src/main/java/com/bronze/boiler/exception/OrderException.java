package com.bronze.boiler.exception;

public class OrderException extends RuntimeException implements BaseException {

    private ExceptionType type;

    public OrderException(ExceptionType type){
        this.type = type;
    }


    @Override
    public ExceptionType getType() {
        return this.type;
    }
}
