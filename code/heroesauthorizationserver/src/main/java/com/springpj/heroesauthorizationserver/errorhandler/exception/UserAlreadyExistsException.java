package com.springpj.heroesauthorizationserver.errorhandler.exception;

public class UserAlreadyExistsException extends ModelException {

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
