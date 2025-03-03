package com.wedding.scoop.exception.badrequest;

public class InvalidOAuthProviderException extends RuntimeException{
    public InvalidOAuthProviderException(String message) {
        super(message);
    }
}
