package com.springpj.heroesauthorizationserver.errorhandler.exception;

public class UserAlreadyExistsException extends ModelException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
