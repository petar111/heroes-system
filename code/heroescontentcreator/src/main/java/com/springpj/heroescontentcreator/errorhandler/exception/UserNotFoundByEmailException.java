package com.springpj.heroescontentcreator.errorhandler.exception;

public class UserNotFoundByEmailException extends NotFoundException{
	
	public UserNotFoundByEmailException(String email) {
		super("User not found by email " + email + ".");
	}

}
