package Exceptions;

public class SillaOcupadaException extends Exception {
	public SillaOcupadaException() {
		super("La silla seleccionada se encuentra ocupada");
	}

}
