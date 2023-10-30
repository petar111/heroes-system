package com.springpj.heroescontentcreator.errorhandler.exception;

public class RoleNotFoundByIdException extends NotFoundException{

	public RoleNotFoundByIdException(Long id) {
		super("Role not found: " + id);
	}
}
