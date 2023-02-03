package com.tribikebackend.exception;

public class TokenNullException extends RuntimeException {
    public TokenNullException(String message) {
        super(message);
    }

    public TokenNullException(String message, Throwable cause) {
        super(message, cause);
    }

}

