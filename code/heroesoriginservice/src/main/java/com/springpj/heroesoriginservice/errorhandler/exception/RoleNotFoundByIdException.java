package com.springpj.heroesoriginservice.errorhandler.exception;

public class RoleNotFoundByIdException extends NotFoundException{

	private static final long serialVersionUID = 7052381933225088636L;

	public RoleNotFoundByIdException(Long id) {
		super("Role not found: " + id);
	}
}
