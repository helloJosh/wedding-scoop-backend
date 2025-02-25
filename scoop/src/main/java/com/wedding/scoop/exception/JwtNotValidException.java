package com.wedding.scoop.exception;

public class JwtNotValidException extends RuntimeException{
    public JwtNotValidException(String message) {
        super(message);
    }
}
