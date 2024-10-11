package TestServicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DTOs.CompraDTO;
import Repositorios.CompraRepositorio;
import Servicios.CompraServicio;

class CompraServicioTest {

	@Mock
	private CompraRepositorio compraRepositorio;
	
	@InjectMocks
	private CompraServicio compraServicio;
	
	@BeforeEach
	void antes() {
		MockitoAnnotations.openMocks(this);
	}
	
	@DisplayName("Test buscar por id")
	@Test
	void testBuscarCompraPorId() {
		CompraDTO compraEsperada = new CompraDTO(1, new Date(0), 1, 5);
		
		when(compraRepositorio.encontrarPorId(1)).thenReturn(compraEsperada);
		CompraDTO resultado = compraServicio.buscarPorId(1);
		
		assertEquals(compraEsperada, resultado);
	}
	
	@DisplayName("Test agregar compra")
	@Test
	void testAgregarCompra() {
		CompraDTO nuevaCompraDTO = new CompraDTO(1, new Date(0), 1, 5);
		compraServicio.agregarCompra(nuevaCompraDTO);
		
		verify(compraRepositorio).guardarCompra(nuevaCompraDTO);
	}
	
	@DisplayName("Test obtener todas las compras de un usuario")
	@Test
	void testObtenerComprasPorUsuario() {
		List<CompraDTO> compras = new ArrayList<CompraDTO>();
		compras.add(new CompraDTO(1, new Date(0), 1, 5));
		compras.add(new CompraDTO(2, new Date(0), 1, 3));
		
		when(compraRepositorio.obtenerComprasPorUsuario(1)).thenReturn(compras);
		
		List<CompraDTO> resultado = compraServicio.obtenerComprasPorUsuario(1);
		assertEquals(compras, resultado);
	}

}
