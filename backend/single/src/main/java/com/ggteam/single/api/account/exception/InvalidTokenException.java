package com.ggteam.single.api.account.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

    private final String errorCode;

    public InvalidTokenException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public InvalidTokenException(String message) {
        super(message);
        this.errorCode = "UnexpectedErrorCode";
    }

}
