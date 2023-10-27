package com.springpj.heroesauthorizationserver.errorhandler.exception;

public class AuthenticationFailedException extends ModelException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
