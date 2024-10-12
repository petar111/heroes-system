package com.springpj.heroesbattletypeservice.errorhandler.exception;

public class AccessTypeNotFoundByNameException  extends NotFoundException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessTypeNotFoundByNameException(String name) {
		super("Access type not found: " + name);
	}

}
