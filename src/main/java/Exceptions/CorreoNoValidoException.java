package Exceptions;

public class CorreoNoValidoException extends Exception{

	public CorreoNoValidoException() {
		super("El correo ingresado no es válido");
	}
}
