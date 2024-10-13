package com.springpj.heroesentityservice.errorhandler.exception;

public class CreatureNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;

	public CreatureNotFoundByIdException(Long id) {
		super("Creature not found by id " + id + ".");
	}

}
