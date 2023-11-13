package com.ggteam.single.api.account.exception;

import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {
    private final String error;

    public TokenException(String error) {
        this.error = error;
    }

}
