package com.springpj.heroescontentcreator.errorhandler.exception;

public class UserNotFoundByEmailException extends NotFoundException{
	
	private static final long serialVersionUID = -4644469072178606657L;

	public UserNotFoundByEmailException(String email) {
		super("User not found by email " + email + ".");
	}

}
