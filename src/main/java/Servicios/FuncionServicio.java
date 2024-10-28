package Servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import Exceptions.CodigoRepetidoException;
import Exceptions.FechaInvalidaException;
import Exceptions.FuncionNoEncontradaException;
import Exceptions.FuncionNoValidaException;
import Exceptions.PeliculaNoEncontradaException;
import Repositorios.FuncionRepositorio;
import Validadores.FuncionValidador;

public class FuncionServicio {

	private FuncionRepositorio funcionRepositorio = new FuncionRepositorio();
	private FuncionValidador funcionValidador = new FuncionValidador();
	
	private PeliculaServicio peliculaServicio = new PeliculaServicio();
	
	public FuncionDTO buscarPorId(int id) throws SQLException, FuncionNoEncontradaException, PeliculaNoEncontradaException {
		FuncionDTO funcion = funcionRepositorio.buscarPorId(id);

		if (funcion == null) {
			throw new FuncionNoEncontradaException();
		}

		return funcion;
	}

	public FuncionDTO buscarPorCodigo(String codigo) throws SQLException, FuncionNoEncontradaException, PeliculaNoEncontradaException {
		FuncionDTO funcion = funcionRepositorio.buscarPorCodigo(codigo);

		if (funcion == null) {
			throw new FuncionNoEncontradaException();
		}
		return funcion;

	}

	public void agregarFuncion(FuncionDTO funcion)
			throws SQLException, FechaInvalidaException, FuncionNoValidaException, PeliculaNoEncontradaException, CodigoRepetidoException {

		if(esFuncionValida(funcion.getCodigo_funcion())) {
			throw new CodigoRepetidoException();
		}
		
		if (!funcionValidador.validarFechaFuncion(funcion)) {
			throw new FechaInvalidaException();
		}

		List<FuncionDTO> funcionesExistentes = funcionRepositorio.obtenerFuncionesPorSala(funcion.getId_sala());

		PeliculaDTO pelicula = peliculaServicio.buscarPorId(funcion.getId_pelicula());
		if (!funcionValidador.validarCruceFunciones(funcion,pelicula.getDuracion_minutos(), funcionesExistentes)) {
			throw new FuncionNoValidaException();
		}

		funcionRepositorio.agregarFuncion(funcion);
	}

	public void eliminarFuncion(String codigo) throws SQLException, FuncionNoEncontradaException, PeliculaNoEncontradaException {
		FuncionDTO funcion = funcionRepositorio.buscarPorCodigo(codigo);
		
		if (funcion == null) {
			throw new FuncionNoEncontradaException();
		}

		funcionRepositorio.eliminarFuncion(codigo);
	}

	public List<FuncionDTO> obtenerFuncionesPorPelicula(int idPelicula) throws SQLException, PeliculaNoEncontradaException {
		return funcionRepositorio.obtenerFuncionesPorPelicula(idPelicula);
	}
	
    public List<FuncionDTO> obtenerTodasLasFunciones() throws SQLException, PeliculaNoEncontradaException {
        return funcionRepositorio.obtenerTodasLasFunciones();
    }

 // Método para validar si la funcion existe por su código
    public boolean esFuncionValida(String codigo) throws SQLException {
        FuncionDTO funcion = funcionRepositorio.buscarPorCodigo(codigo);
        if(funcion == null) {
        	return false;
        }  
        return true;
    }
    
    public List<PeliculaDTO> obtenerPeliculasConFunciones() throws SQLException, PeliculaNoEncontradaException{
    	List<Integer> idsPeliculas = funcionRepositorio.getPeliculasConFunciones();
    	List<PeliculaDTO> peliculas = new ArrayList<PeliculaDTO>();
    	for(Integer i:idsPeliculas) {
    		PeliculaDTO pelicula = peliculaServicio.buscarPorId(i);
    		peliculas.add(pelicula);
    	}
    	return peliculas;
    }
    
    public boolean funcionYaPaso(FuncionDTO funcion) {
    	return funcionValidador.funcionYaPaso(funcion);
    }
}
