package Servicios;

import java.sql.SQLException;
import java.util.List;

import DTOs.PeliculaDTO;
import Exceptions.DuracionInvalidaException;
import Exceptions.FechaInvalidaException;
import Exceptions.PeliculaExistenteException;
import Exceptions.PeliculaNoEncontradaException;
import Repositorios.PeliculaRepositorio;
import Validadores.PeliculaValidador;

public class PeliculaServicio {
	
	private PeliculaRepositorio peliculaRepositorio = new PeliculaRepositorio();
	private PeliculaValidador peliculaValidador = new PeliculaValidador();
	
    public PeliculaDTO buscarPorId(int id) throws SQLException, PeliculaNoEncontradaException {
        PeliculaDTO pelicula = peliculaRepositorio.buscarPorId(id);
        
        if (!esPeliculaValida(pelicula.getCodigo_pelicula())) {
        	throw new PeliculaNoEncontradaException();
        }
        
        return pelicula;
        
    }
    
    public PeliculaDTO buscarPorCodigo(String codigo) throws SQLException, PeliculaNoEncontradaException {
        PeliculaDTO pelicula = peliculaRepositorio.buscarPorCodigo(codigo);

        
        if (!esPeliculaValida(codigo)) {
        	throw new PeliculaNoEncontradaException();
        }

        return pelicula;
        
    }
    
    public void agregarPelicula(PeliculaDTO pelicula) throws SQLException, FechaInvalidaException, PeliculaExistenteException, DuracionInvalidaException {
 
        if (!peliculaValidador.validarFechaEstreno(pelicula)) {
            throw new FechaInvalidaException();
        }


        if (esPeliculaValida(pelicula.getCodigo_pelicula())) {
        	throw new PeliculaExistenteException();
        }
        
        if(!peliculaValidador.validarDuracion(pelicula)) {
        	throw new DuracionInvalidaException();
        }
        peliculaRepositorio.agregarPelicula(pelicula);
        
    }
    
    public List<PeliculaDTO> obtenerPeliculas() throws SQLException {
        return peliculaRepositorio.obetenerPeliculas();
    }
    
    public void eliminarPelicula(String codigo) throws SQLException, PeliculaNoEncontradaException {
    	
    	if (!esPeliculaValida(codigo)) {
    	    throw new PeliculaNoEncontradaException(); 
    	}
        
        peliculaRepositorio.eliminarPelicula(codigo);
    }
    
    public void actualizarPelicula(String codigo, PeliculaDTO nuevaPelicula) throws SQLException, PeliculaNoEncontradaException, FechaInvalidaException, DuracionInvalidaException {
        
    	if (!esPeliculaValida(codigo)) {
    	    throw new PeliculaNoEncontradaException();
    	}

        if (!peliculaValidador.validarFechaEstreno(nuevaPelicula)) {
            throw new FechaInvalidaException();
        }
        
        if(!peliculaValidador.validarDuracion(nuevaPelicula)) {
        	throw new DuracionInvalidaException();
        }

        peliculaRepositorio.actualizarPelicula(codigo, nuevaPelicula);
    }

    public boolean esPeliculaValida(String codigo) throws SQLException {
    	
        PeliculaDTO pelicula = peliculaRepositorio.buscarPorCodigo(codigo);
        
        if(pelicula == null) {
        	return false;
        }  
        
        return true;
    }
    
    
}

