package TestServicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DTOs.PeliculaDTO;
import Exceptions.PeliculaNoEncontradaException;
import Repositorios.PeliculaRepositorio;
import Servicios.PeliculaServicio;
import Validadores.PeliculaValidador;

class PeliculaServicioTest { 


    @Mock
    private PeliculaRepositorio peliculaRepositorio;

    @Mock
    private PeliculaValidador peliculaValidador;

    @InjectMocks
    private PeliculaServicio peliculaServicio;

    @BeforeEach
    void antes() {
        MockitoAnnotations.openMocks(this);
    }
    
//	@DisplayName("Test buscar pelicula por Id")
//	@Test
//	void buscarPeliculaPorIdTest() throws SQLException, PeliculaNoEncontradaException {
//		PeliculaDTO peliculaEsperada = new PeliculaDTO(1, "1", "Mulán", new Date(0), "Disney", 120, "Aventura", "1");
//		
//		when(peliculaRepositorio.buscarPorId(1)).thenReturn(peliculaEsperada);
//		when(peliculaRepositorio.buscarPorCodigo("1")).thenReturn(peliculaEsperada);
//		
//		when(peliculaServicio.esPeliculaValida("1")).thenReturn(true);
//		PeliculaDTO resultado = peliculaServicio.buscarPorId(1);
//		
//		assertEquals(peliculaEsperada, resultado);
//	}
	
  

}
