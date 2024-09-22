package com.springpj.heroesoriginservice.errorhandler.exception;

public class UserNotFoundByUsernameException extends NotFoundException{

	private static final long serialVersionUID = 3267134420286244470L;

	public UserNotFoundByUsernameException(String username) {
		super("User not found by username " + username + ".");
	}

}
