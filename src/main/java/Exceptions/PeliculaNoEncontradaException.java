package Exceptions;

public class PeliculaNoEncontradaException extends Exception{

    public PeliculaNoEncontradaException() {
        super("La pelicula no ha sido encontrada");
    }
    
}
