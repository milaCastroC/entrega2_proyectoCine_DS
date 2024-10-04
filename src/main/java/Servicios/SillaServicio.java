package Servicios;

import DTOs.SalaDTO;
import DTOs.SillaDTO;
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
}
