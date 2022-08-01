package com.bronze.boiler.domain.member.exception;

/**
 * 예외인터페이스
 * 추상화하면서 핸들링 로직을 단순화
 */
public interface BaseException {

    ExceptionType getType();


}
