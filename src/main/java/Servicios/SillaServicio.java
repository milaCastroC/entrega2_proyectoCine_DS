package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.SalaDTO;
import DTOs.SillaDTO;
import DatabaseConfig.DatabaseConfig;
import Repositorios.SillaRepositorio;

public class SillaServicio {

	private SillaRepositorio sillaRepositorio = new SillaRepositorio();
	
	
	public SillaDTO buscarSillaPorId(int id) {
		return sillaRepositorio.buscarSillaPorId(id);
	}
	
	public void guardarSilla(SillaDTO silla) {
		sillaRepositorio.guardarSilla(silla);
	}
	
	public void eliminarSillaPorNumSala(int numeroSilla, int idSala) {
		sillaRepositorio.eliminarSillaPorNumSala(numeroSilla, idSala);
	}
	
	public SillaDTO[][] obtenerMatrizSillas(SalaDTO sala){
		return sillaRepositorio.obtenerMatrizSillas(sala);
	}
	
	public List<SillaDTO> obtenerSillasOcupadasPorFuncion(int idFuncion) {
       return sillaRepositorio.obtenerSillasOcupadasPorFuncion(idFuncion);
    }
	
	
}
