package Exceptions;

public class SalaNoEncontradaException extends Exception{

	public SalaNoEncontradaException() {
		super("No se encontró la sala buscada");
	}
	
}
