package dev.as.curso.udemy.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Entidade não encontrada com ID: "+id);
	}


}
