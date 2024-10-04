package Exceptions;

public class CapacidadNoValidaException extends Exception{

	public CapacidadNoValidaException() {
		super("Capacidad no válida. Recuerde que el rango permitido es 10 - 50");
	}
}
