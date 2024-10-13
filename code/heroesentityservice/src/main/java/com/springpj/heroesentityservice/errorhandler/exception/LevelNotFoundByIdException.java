package com.springpj.heroesentityservice.errorhandler.exception;

public class LevelNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;

	public LevelNotFoundByIdException(Long id) {
		super("Level not found by id " + id + ".");
	}

}
