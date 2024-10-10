package TestValidadores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Validadores.SalaValidador;

class SalaValidadorTest {

	private SalaValidador salaValidador = new SalaValidador();
	
	@DisplayName("Test validar capacidad de la sala")
	@Test
	void validarCapacidadValida() {
		assertTrue(salaValidador.validarCapacidad(45));
	}
	
	@DisplayName("Test capacidad no válida de la sala")
	@Test
	void validarCapacidadNoValida() {
		assertFalse(salaValidador.validarCapacidad(2));
	}

}
