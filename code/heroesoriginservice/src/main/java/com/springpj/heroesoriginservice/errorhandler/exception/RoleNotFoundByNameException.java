package com.springpj.heroescontentcreator.errorhandler.exception;

public class RoleNotFoundByNameException extends NotFoundException{

	private static final long serialVersionUID = -4029096177965920114L;

	public RoleNotFoundByNameException(String name) {
		super("Role not found: " + name);
	}
}
