package Exceptions;

public class CodigoRepetidoException extends Exception{

	public CodigoRepetidoException() {
		super("El codigo ingresado ya existe");
	}
}
