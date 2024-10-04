package Controladores;

import java.sql.SQLException;
import java.util.List;

import DTOs.PeliculaDTO;
import Exceptions.DuracionInvalidaException;
import Exceptions.FechaInvalidaException;
import Exceptions.PeliculaExistenteException;
import Exceptions.PeliculaNoEncontradaException;
import Servicios.PeliculaServicio;

public class ControladorVentanaGestionPelicula {
	
	private PeliculaServicio peliculaServicio = new PeliculaServicio();
	
	public PeliculaDTO buscarPorId(int id) throws SQLException, PeliculaNoEncontradaException{
		return peliculaServicio.buscarPorId(id);
    }
    
    public PeliculaDTO buscarPorCodigo(String codigo) throws SQLException, PeliculaNoEncontradaException{
            return peliculaServicio.buscarPorCodigo(codigo);
    }
    
    public void agregarPelicula(PeliculaDTO pelicula) throws SQLException, FechaInvalidaException, PeliculaExistenteException, DuracionInvalidaException{
    	peliculaServicio.agregarPelicula(pelicula);
    }
    
    public List<PeliculaDTO> obtenerPeliculas() throws SQLException {
    	return peliculaServicio.obtenerPeliculas();
    }
    
    public void eliminarPelicula(String codigo) throws SQLException, PeliculaNoEncontradaException {
    	peliculaServicio.eliminarPelicula(codigo);
    }
    
    public void actualizarPelicula(String codigo, PeliculaDTO nuevaPelicula) throws SQLException, PeliculaNoEncontradaException, FechaInvalidaException, DuracionInvalidaException {
    	 peliculaServicio.actualizarPelicula(codigo, nuevaPelicula);
    }
}
