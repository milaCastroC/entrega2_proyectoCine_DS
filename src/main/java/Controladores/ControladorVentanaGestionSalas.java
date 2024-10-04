package Controladores;

import java.util.List;

import DTOs.SalaDTO;
import Exceptions.CapacidadNoValidaException;
import Exceptions.CodigoRepetidoException;
import Exceptions.SalaNoEncontradaException;
import Servicios.SalaServicio;

public class ControladorVentanaGestionSalas {

	private SalaServicio salaServicio = new SalaServicio();
	
	public SalaDTO buscarPorCodigo(String codigo) throws SalaNoEncontradaException {
		SalaDTO sala = salaServicio.buscarPorCodigo(codigo);
		if(sala != null) {
			return sala; 
		}
		throw new SalaNoEncontradaException();
	}
	
	public List<SalaDTO> getSalas() {
		return salaServicio.getSalas();
	}
	
	public void agregarSala(SalaDTO sala) throws CodigoRepetidoException, CapacidadNoValidaException {
		String codigo = sala.getCodigoSala();
		SalaDTO salaExistente = salaServicio.buscarPorCodigo(codigo);
		if(salaExistente == null) {
			salaServicio.agregarSala(sala);
		} else {
			throw new CodigoRepetidoException();
		}
		

	}
	
	public void eliminarSala(String codigo) throws SalaNoEncontradaException {
		buscarPorCodigo(codigo);
		salaServicio.eliminarSala(codigo);
	}
	
	public void actualizarSala(SalaDTO sala) throws SalaNoEncontradaException, CapacidadNoValidaException {
		buscarPorCodigo(sala.getCodigoSala());
		salaServicio.actualizarSala(sala);
	}
}
