package com.springpj.heroesuserservice.errorhandler.exception;

public class ResourceNotFoundByNameException extends NotFoundException {

	private static final long serialVersionUID = -6455095998637024184L;

	public ResourceNotFoundByNameException(String name) {
		super("Resource not found: " + name);
	}
	
}
