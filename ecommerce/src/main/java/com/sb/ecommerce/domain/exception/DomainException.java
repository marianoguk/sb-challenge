package com.sb.ecommerce.domain.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final ExceptionType type;

    public DomainException(ExceptionType type) {
        this.type = type;
    }

}
