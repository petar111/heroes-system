package com.springpj.heroesentityservice.errorhandler.exception;

public class HeroNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;

	public HeroNotFoundByIdException(Long id) {
		super("Hero not found by id " + id + ".");
	}

}
