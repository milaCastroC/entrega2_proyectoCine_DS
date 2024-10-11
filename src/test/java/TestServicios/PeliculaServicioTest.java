package TestServicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DTOs.PeliculaDTO;
import Exceptions.DuracionInvalidaException;
import Exceptions.FechaInvalidaException;
import Exceptions.PeliculaExistenteException;
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
    
    @DisplayName("Test buscar pelicula por Id")
    @Test
    void testBuscarPeliculaPorId() throws SQLException, PeliculaNoEncontradaException {
        PeliculaDTO peliculaEsperada = new PeliculaDTO(1, "1", null, null, null, 0, null, null);
        
        when(peliculaRepositorio.buscarPorId(1)).thenReturn(peliculaEsperada);
        when(peliculaRepositorio.buscarPorCodigo("1")).thenReturn(peliculaEsperada);

        PeliculaDTO resultado = peliculaServicio.buscarPorId(1);
        assertEquals(peliculaEsperada, resultado);
    }
    
    @DisplayName("Test buscar pelicula por Id no existente")
    @Test
    void testBuscarPeliculaPorIdNoEncontrada() throws SQLException {
        PeliculaDTO nuevaPelicula = new PeliculaDTO(1, "1", "Mulán", null, "Disney", 120, "Aventura", "Muy buena");

        when(peliculaRepositorio.buscarPorId(55)).thenReturn(nuevaPelicula);

        assertThrows(PeliculaNoEncontradaException.class, () -> {
        	peliculaServicio.buscarPorId(55);
        }, "PeliculaNoEncontradaException es lanzada cuando no se encuentra la pelicula buscada");
    }
    
    @DisplayName("Test buscar pelicula por codigo")
    @Test
    void testBuscarPeliculaPorCodigo() throws SQLException, PeliculaNoEncontradaException {
        PeliculaDTO peliculaEsperada = new PeliculaDTO(1, "1", null, null, null, 0, null, null);
        
        when(peliculaRepositorio.buscarPorId(1)).thenReturn(peliculaEsperada);
        when(peliculaRepositorio.buscarPorCodigo("1")).thenReturn(peliculaEsperada);
        
        PeliculaDTO resultado = peliculaServicio.buscarPorId(1);
        assertEquals(peliculaEsperada, resultado);
    }
    
    @DisplayName("Test buscar pelicula por codigo no existente")
    @Test
    void testBuscarPeliculaPorCodigoNoEncontrada() throws SQLException {
        when(peliculaRepositorio.buscarPorCodigo("1")).thenReturn(null);

        assertThrows(PeliculaNoEncontradaException.class, () -> {peliculaServicio.buscarPorCodigo("1");
        }, "PeliculaNoEncontradaException es lanzada cuando no se encuentra la pelicula buscada");
    }
    
    @DisplayName("Test agregar pelicula")
    @Test
    void testAgregarPelicula() throws SQLException, FechaInvalidaException, PeliculaExistenteException, DuracionInvalidaException {
        PeliculaDTO nuevaPelicula = new PeliculaDTO(1, "1", "Mulán", null, "Disney", 120, "Aventura", "Muy buena");

        when(peliculaValidador.validarFechaEstreno(nuevaPelicula)).thenReturn(true);
        when(peliculaValidador.validarDuracion(nuevaPelicula)).thenReturn(true);
        when(peliculaRepositorio.buscarPorCodigo("1")).thenReturn(null);

        peliculaServicio.agregarPelicula(nuevaPelicula);

        verify(peliculaRepositorio).agregarPelicula(nuevaPelicula);
    }

    @DisplayName("Test agregar pelicula - Fecha invalida")
    @Test
    void testAgregarPeliculaFechaInvalida() throws SQLException {
        PeliculaDTO nuevaPelicula = new PeliculaDTO(1, "1", "Mulán", null, "Disney", 120, "Aventura", "Muy buena");
        when(peliculaValidador.validarFechaEstreno(nuevaPelicula)).thenReturn(false);

        assertThrows(FechaInvalidaException.class, () -> peliculaServicio.agregarPelicula(nuevaPelicula));
    }

    @DisplayName("Test eliminar pelicula")
    @Test
    void testEliminarPelicula() throws SQLException, PeliculaNoEncontradaException {
        PeliculaDTO peliculaAEliminar = new PeliculaDTO(1, "1", "Mulán", null, "Disney", 120, "Aventura", "Muy buena");

        when(peliculaRepositorio.buscarPorCodigo("1")).thenReturn(peliculaAEliminar);

        peliculaServicio.eliminarPelicula("1");
        verify(peliculaRepositorio).eliminarPelicula("1");
    }

    @DisplayName("Test eliminar pelicula - Pelicula no encontrada")
    @Test
    void testEliminarPeliculaNoEncontrada() throws SQLException {
        when(peliculaRepositorio.buscarPorCodigo("1")).thenReturn(null);

        assertThrows(PeliculaNoEncontradaException.class, () -> {peliculaServicio.eliminarPelicula("1");
        },"PeliculaNoEncontradaException es lanzada cuando no se encuentra la pelicula buscada");
    }

    @DisplayName("Test actualizar pelicula")
    @Test
    void testActualizarPelicula() throws SQLException, PeliculaNoEncontradaException, FechaInvalidaException, DuracionInvalidaException {
        PeliculaDTO peliculaExistente = new PeliculaDTO(1, "1", "Mulán", null, "Disney", 120, "Aventura", "Muy buena");
        PeliculaDTO nuevaPelicula = new PeliculaDTO(2, "2", "Mulán 2", null, "Disney2", 130, "Aventura y Drama", "También es buena, aunque no tanto como la primera");

        when(peliculaRepositorio.buscarPorCodigo("1")).thenReturn(peliculaExistente);
        when(peliculaValidador.validarFechaEstreno(nuevaPelicula)).thenReturn(true);
        when(peliculaValidador.validarDuracion(nuevaPelicula)).thenReturn(true);

        peliculaServicio.actualizarPelicula("1", nuevaPelicula);

        verify(peliculaRepositorio).actualizarPelicula("1", nuevaPelicula);
    }

    @DisplayName("Test actualizar pelicula - Fecha invalida")
    @Test
    void testActualizarPeliculaFechaInvalida() throws SQLException {
        PeliculaDTO nuevaPelicula = new PeliculaDTO(1, "1", "Nuevo Titulo", null, "Nuevo Director", 130, "Genero", "Sinopsis");

        when(peliculaRepositorio.buscarPorCodigo("1")).thenReturn(nuevaPelicula);
        when(peliculaValidador.validarFechaEstreno(nuevaPelicula)).thenReturn(false);

        assertThrows(FechaInvalidaException.class, () -> peliculaServicio.actualizarPelicula("1", nuevaPelicula));
    }

    @DisplayName("Test obtener peliculas")
    @Test
    void testObtenerPeliculas() throws SQLException {
        List<PeliculaDTO> peliculasEsperadas = new ArrayList<>();
        peliculasEsperadas.add( new PeliculaDTO(1, "1", "Mulán", null, "Disney", 120, "Aventura", "Muy buena"));
        peliculasEsperadas.add( new PeliculaDTO(2, "2", "Mulán2", null, "Disney", 100, "Aventura", "Muy buena"));
        when(peliculaRepositorio.obetenerPeliculas()).thenReturn(peliculasEsperadas);

        List<PeliculaDTO> resultado = peliculaServicio.obtenerPeliculas();
        assertEquals(peliculasEsperadas, resultado);
    }
	
  

}
