package Exceptions;

public class PeliculaExistenteException extends Exception {
	
	public PeliculaExistenteException() {
		super("La pelicula ya se encuentra registrada");
	}
}
