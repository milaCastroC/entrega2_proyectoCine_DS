package Exceptions;

public class FuncionNoValidaException extends Exception {
	
	public FuncionNoValidaException() {
		super("La función no se válida, La función se cruza con otra existente en la misma sala");
	}

}
