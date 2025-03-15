package com.wedding.scoop.exception.notfound;

public class OAuthNotFoundException extends RuntimeException{
    public OAuthNotFoundException(String message) {
        super(message);
    }
}
