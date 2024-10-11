package TestServicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DTOs.SalaDTO;
import DTOs.SillaDTO;
import Repositorios.SillaRepositorio;
import Servicios.SillaServicio;

class SillaServicioTest {

	@Mock
	private SillaRepositorio sillaRepositorio;
	
	@InjectMocks
	private SillaServicio sillaServicio;
	
	@BeforeEach
	void antes() {
		MockitoAnnotations.openMocks(this);
	}
	
	@DisplayName("Test buscar silla por id")
	@Test
	void buscarSillaPorIdTest() {
		SillaDTO sillaEsperada = new SillaDTO(1, 1, 1);
		when(sillaRepositorio.buscarSillaPorId(1)).thenReturn(sillaEsperada);
		SillaDTO resultado = sillaServicio.buscarSillaPorId(1);
		
		assertEquals(sillaEsperada, resultado);
	}
	
	@DisplayName("Test buscar silla por id no valido")
	@Test
	void testBuscarSillaPorIdNoValido() {
		when(sillaRepositorio.buscarSillaPorId(999)).thenReturn(null);

		SillaDTO sillaResultado = sillaServicio.buscarSillaPorId(999);

		assertNull(sillaResultado);
	}
	
	@DisplayName("Test guardar silla")
	@Test
	void testGuardarSilla() {
		SillaDTO nuevaSilla = new SillaDTO(1, 1, 1);

		sillaServicio.guardarSilla(nuevaSilla);

		verify(sillaRepositorio).guardarSilla(nuevaSilla);
	}

	@DisplayName("Test eliminar silla por número y sala")
	@Test
	void testEliminarSillaPorNumSala() {
		int numeroSilla = 1;
		int idSala = 1;

		sillaServicio.eliminarSillaPorNumSala(numeroSilla, idSala);

		verify(sillaRepositorio).eliminarSillaPorNumSala(numeroSilla, idSala);
	}
	

	@DisplayName("Test obtener matriz de sillas")
	@Test
	void testObtenerMatrizSillas() {
		SalaDTO sala = new SalaDTO(1, "1", "Sala 1", 20);
		SillaDTO[][] matrizEsperada = new SillaDTO[4][5];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				matrizEsperada[i][j] = new SillaDTO(1, 1 ,1);
			}
		}

		when(sillaRepositorio.obtenerMatrizSillas(sala)).thenReturn(matrizEsperada);

		SillaDTO[][] matrizResultado = sillaServicio.obtenerMatrizSillas(sala);

		assertArrayEquals(matrizEsperada, matrizResultado);
	}

	@DisplayName("Test obtener sillas ocupadas por función")
	@Test
	void testObtenerSillasOcupadasPorFuncion() {
		int idFuncion = 1;
		List<SillaDTO> sillasOcupadas = new ArrayList<>();
		sillasOcupadas.add(new SillaDTO(1, 1, 1));
		sillasOcupadas.add(new SillaDTO(2, 2, 1));

		when(sillaRepositorio.obtenerSillasOcupadasPorFuncion(idFuncion)).thenReturn(sillasOcupadas);

		List<SillaDTO> sillasResultado = sillaServicio.obtenerSillasOcupadasPorFuncion(idFuncion);

		assertEquals(sillasOcupadas, sillasResultado);
	}
	
	@DisplayName("Test obtener sillas ocupadas para función sin ocupación")
	@Test
	void testObtenerSillasOcupadasPorFuncionVacia() {
		int idFuncionVacia = 2;
		List<SillaDTO> sillasVacias = new ArrayList<>();

		when(sillaRepositorio.obtenerSillasOcupadasPorFuncion(idFuncionVacia)).thenReturn(sillasVacias);

		List<SillaDTO> sillasResultado = sillaServicio.obtenerSillasOcupadasPorFuncion(idFuncionVacia);

		assertTrue(sillasResultado.isEmpty());
	}

	

	

}
