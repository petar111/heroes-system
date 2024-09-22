package com.springpj.heroesoriginservice.errorhandler.exception;

public class UserAlreadyExistsException extends ModelException {

	private static final long serialVersionUID = 3634964066464130564L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
