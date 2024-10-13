package com.springpj.heroesentityservice.errorhandler.exception;

public class EntityDefinitionNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;
	
	public EntityDefinitionNotFoundByIdException(Long id) {
		super("Entity not found by id " + id + ".");
	}

}
