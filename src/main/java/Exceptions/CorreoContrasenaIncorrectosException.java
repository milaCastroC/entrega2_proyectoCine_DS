package Exceptions;

public class CorreoContrasenaIncorrectosException extends Exception{
	
	public CorreoContrasenaIncorrectosException() {
		super("El correo o la contraseña son incorrectos");
	}
}
