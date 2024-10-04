package Controladores;

import java.sql.SQLException;
import java.util.List;

import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import DTOs.SalaDTO;
import Exceptions.CodigoRepetidoException;
import Exceptions.FechaInvalidaException;
import Exceptions.FuncionNoEncontradaException;
import Exceptions.FuncionNoValidaException;
import Exceptions.PeliculaNoEncontradaException;
import Servicios.FuncionServicio;
import Servicios.PeliculaServicio;
import Servicios.SalaServicio;

public class ControladorVentanaGestionFunciones {
	
	private FuncionServicio funcionServicio = new FuncionServicio();
	
	private PeliculaServicio peliculaServicio = new PeliculaServicio();
	private SalaServicio salaServicio = new SalaServicio();

	public FuncionDTO buscarPorId(int id) throws SQLException, FuncionNoEncontradaException, PeliculaNoEncontradaException {
		return funcionServicio.buscarPorId(id);
	}
	
	public FuncionDTO buscarPorCodigo(String codigo) throws SQLException, FuncionNoEncontradaException, PeliculaNoEncontradaException {
		return funcionServicio.buscarPorCodigo(codigo);
	}
	
	public void agregarFuncion(FuncionDTO funcion)throws SQLException, FechaInvalidaException, FuncionNoValidaException, PeliculaNoEncontradaException, CodigoRepetidoException {
		funcionServicio.agregarFuncion(funcion);
	}
	
	public void eliminarFuncion(String codigo) throws SQLException, FuncionNoEncontradaException, PeliculaNoEncontradaException {
		funcionServicio.eliminarFuncion(codigo);
	}
	
	public List<FuncionDTO> obtenerFuncionesPorPelicula(int idPelicula) throws SQLException, PeliculaNoEncontradaException {
		return funcionServicio.obtenerFuncionesPorPelicula(idPelicula);
	}
	
    public List<FuncionDTO> obtenerTodasLasFunciones() throws SQLException, PeliculaNoEncontradaException {
        return funcionServicio.obtenerTodasLasFunciones();
    }

    public List<PeliculaDTO> obtenerTodasLasPeliculas() throws SQLException, PeliculaNoEncontradaException {
        return peliculaServicio.obtenerPeliculas();
    }

    public List<SalaDTO> obtenerTodasLasSalas() throws SQLException {
        return salaServicio.getSalas();
    }
    
    public SalaDTO obtenerSala(int id) {
    	return salaServicio.buscarPorId(id);
    }
    
    public PeliculaDTO obtenerPelicula(int id) throws SQLException, PeliculaNoEncontradaException {
    	return peliculaServicio.buscarPorId(id);
    }
	
}
