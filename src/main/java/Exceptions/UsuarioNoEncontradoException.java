package Exceptions;

public class UsuarioNoEncontradoException extends RuntimeException{
	
	public UsuarioNoEncontradoException() {
		super("El usuario ingresado no se encuentra registrado");
	}
	
}
