package Servicios;

import java.util.List;

import DTOs.SalaDTO;
import DTOs.SillaDTO;
import Exceptions.CapacidadNoValidaException;
import Repositorios.SalaRepositorio;
import Validadores.SalaValidador;

public class SalaServicio {
	
	private SalaRepositorio salaRepositorio = new SalaRepositorio();
	private SalaValidador salaValidador = new SalaValidador();
	
	private SillaServicio sillaServicio = new SillaServicio();
	
	public SalaDTO buscarPorCodigo(String codigo) {
		int id = salaRepositorio.getIdSala(codigo);
		return salaRepositorio.buscarPorId(id);
	}
	
	public SalaDTO buscarPorId(int id) {
		return salaRepositorio.buscarPorId(id);
	}
	
	public int getIdSala(String codigo) {
		return salaRepositorio.getIdSala(codigo);
	}
	
	public List<SalaDTO> getSalas() {
		return salaRepositorio.getSalas();
	}
	
	public void agregarSala(SalaDTO sala) throws CapacidadNoValidaException {
		if(salaValidador.validarCapacidad(sala.getCapacidad())) {
			salaRepositorio.guardarSala(sala);
			agregarSillasPorSala(sala);
		} else {
			throw new CapacidadNoValidaException();
		}
		
	}
	
	public void agregarSillasPorSala(SalaDTO sala) {
		int idSala = getIdSala(sala.getCodigoSala());
		for (int i = 1; i <= sala.getCapacidad(); i++) {
            SillaDTO silla = new SillaDTO(0, i, idSala);
            sillaServicio.guardarSilla(silla);;
        }
	}
	
	public void eliminarSala(String codigo) {
		int id = salaRepositorio.getIdSala(codigo);
		salaRepositorio.eliminarSala(id);
	}
	
	public void actualizarSala(SalaDTO sala) throws CapacidadNoValidaException {
		if(salaValidador.validarCapacidad(sala.getCapacidad())) {
			SalaDTO salaGuardada = salaRepositorio.buscarPorId((sala.getId_sala()));
			int capacidadActual = salaGuardada.getCapacidad();
			int nuevaCapacidad = sala.getCapacidad();
			
			salaRepositorio.actualizarSala(sala);
			
			if(capacidadActual != nuevaCapacidad) {
				actualizarCapacidad(capacidadActual, nuevaCapacidad, sala.getId_sala());
			}
		}else {
			throw new CapacidadNoValidaException();
		}
	}
	
	public void actualizarCapacidad(int capacidadActual, int nuevaCapacidad, int idSala) {
		
		if (nuevaCapacidad > capacidadActual) {
			for (int i = capacidadActual + 1; i <= nuevaCapacidad; i++) {
				SillaDTO silla = new SillaDTO(0,i,idSala);
				sillaServicio.guardarSilla(silla);
			}
		} else if (nuevaCapacidad < capacidadActual) {
		    for (int i = capacidadActual; i > nuevaCapacidad; i--) {
		    	sillaServicio.eliminarSillaPorNumSala(i, idSala);

		    }
		}
	}
}
