package com.higorpalmeira.github.gerenciadorconsultas.model.exceptions;

public class DataConflictException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataConflictException(String aMessage) {
		super(aMessage);
	}
	
}
