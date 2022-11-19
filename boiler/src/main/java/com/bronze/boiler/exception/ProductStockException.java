package com.bronze.boiler.exception;

public class ProductStockException extends RuntimeException implements BaseException {

    private ExceptionType type;

    public ProductStockException(ExceptionType type){
        this.type = type;
    }

    @Override
    public ExceptionType getType() {
        return this.type;
    }
}
