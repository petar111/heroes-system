package com.springpj.heroesfactionservice.errorhandler.exception;

public class FactionNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;
	
	public FactionNotFoundByIdException(Long id) {
		super("Faction not found by id " + id + ".");
	}

}
