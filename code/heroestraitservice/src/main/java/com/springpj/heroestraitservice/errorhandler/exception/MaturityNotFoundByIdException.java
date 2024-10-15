package com.springpj.heroestraitservice.errorhandler.exception;

public class MaturityNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;

	public MaturityNotFoundByIdException(Long id) {
		super("Maturity not found by id " + id + ".");
	}

}
