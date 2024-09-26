package com.springpj.heroesfactionservice.errorhandler.exception;

public class AuthenticationFailedException extends ModelException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationFailedException(String message) {
        super(message);
    }
}