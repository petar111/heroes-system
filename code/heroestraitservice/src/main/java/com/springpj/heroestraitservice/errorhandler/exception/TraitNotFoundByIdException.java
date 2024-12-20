package com.springpj.heroestraitservice.errorhandler.exception;

public class TraitNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;
	
	public TraitNotFoundByIdException(Long id) {
		super("Trait not found by id " + id + ".");
	}

}
