package com.springpj.heroesfactionservice.errorhandler.exception;

public class NotFoundException extends ModelException {

	private static final long serialVersionUID = -4968348924588570481L;

	public NotFoundException(String message) {
		super(message);
	}

}
