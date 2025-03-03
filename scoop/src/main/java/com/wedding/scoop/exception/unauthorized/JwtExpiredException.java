package com.wedding.scoop.exception.unauthorized;

public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException(String message) {
        super(message);
    }
}
