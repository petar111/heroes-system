package com.springpj.heroescompanyservice.errorhandler.exception;

public class CompanyNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;
	
	public CompanyNotFoundByIdException(Long id) {
		super("Company not found by id " + id + ".");
	}

}
