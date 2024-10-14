package com.springpj.heroesspellservice.errorhandler.exception;

public class SpellNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;
	
	public SpellNotFoundByIdException(Long id) {
		super("Spell not found by id " + id + ".");
	}

}
