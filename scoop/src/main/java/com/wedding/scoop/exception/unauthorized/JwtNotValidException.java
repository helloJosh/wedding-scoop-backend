package com.wedding.scoop.exception.unauthorized;

public class JwtNotValidException extends RuntimeException{
    public JwtNotValidException(String message) {
        super(message);
    }
}
