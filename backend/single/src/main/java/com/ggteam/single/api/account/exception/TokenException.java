package com.ggteam.single.api.account.exception;

public class TokenException extends RuntimeException {
    private final String errorCode;
    private final String definition;

    public TokenException(String errorCode, String definition) {
        super(definition);
        this.errorCode = errorCode;
        this.definition = definition;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDefinition() {
        return definition;
    }
}
