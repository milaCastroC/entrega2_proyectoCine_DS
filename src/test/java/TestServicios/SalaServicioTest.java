package TestServicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DTOs.SalaDTO;
import DTOs.SillaDTO;
import Exceptions.CapacidadNoValidaException;
import Repositorios.SalaRepositorio;
import Validadores.SalaValidador;
import Servicios.SalaServicio;
import Servicios.SillaServicio;

class SalaServicioTest {

    
    @Mock
    private SalaRepositorio salaRepositorio;

    @Mock
    private SalaValidador salaValidador;

    @Mock
    private SillaServicio sillaServicio;

    @InjectMocks
    private SalaServicio salaServicio;
    

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        

    }

    @DisplayName("Test buscar sala por codigo")
    @Test
    void testBuscarPorCodigo() {
    	SalaDTO salaEsperada = new SalaDTO(1, "1", "Sala 1", 50);
        when(salaRepositorio.getIdSala("1")).thenReturn(1);
        when(salaRepositorio.buscarPorId(1)).thenReturn(salaEsperada);

        SalaDTO salaResultado = salaServicio.buscarPorCodigo("1");

        assertEquals(salaEsperada, salaResultado);
    }

    @DisplayName("Test obtener todas las salas")
    @Test
    void testGetSalas() {
        List<SalaDTO> salas = new ArrayList<>();
        salas.add(new SalaDTO(1, "1", "Sala 1", 50));
        salas.add(new SalaDTO(2, "2", "Sala 2", 20));
         
        when(salaRepositorio.getSalas()).thenReturn(salas);
        List<SalaDTO> salasResultado = salaServicio.getSalas();

        assertEquals(salas, salasResultado);
    }

    @DisplayName("Test agregar nueva sala")
    @Test
    void testAgregarSala() throws CapacidadNoValidaException {
        SalaDTO nuevaSala = new SalaDTO(3, "3", "Sala 3", 40);

        when(salaValidador.validarCapacidad(nuevaSala.getCapacidad())).thenReturn(true);
        when(salaRepositorio.getIdSala(nuevaSala.getCodigoSala())).thenReturn(3);

        salaServicio.agregarSala(nuevaSala);

        verify(salaRepositorio).guardarSala(nuevaSala);
        verify(sillaServicio, times(40)).guardarSilla(any(SillaDTO.class)); // Verifica que se agreguen las 40 sillas
    }

    @DisplayName("Test agregar nueva sala con una capacidad no válida")
    @Test
    void testAgregarSalaCapacidadNoValida() {
        SalaDTO nuevaSala = new SalaDTO(1, "1", "Sala 1", 0);

        when(salaValidador.validarCapacidad(nuevaSala.getCapacidad())).thenReturn(false);

        assertThrows(CapacidadNoValidaException.class, () -> {
        	salaServicio.agregarSala(nuevaSala);
        	}, "CapacidadNoValidaException se lanza cuando la capacidad de la sala no es valida");
    }

    @DisplayName("Test actualizar sala")
    @Test
    void testActualizarSala() throws CapacidadNoValidaException {
        SalaDTO salaOriginal = new SalaDTO(1, "1", "Sala Normi", 30);
        SalaDTO salaActualizada = new SalaDTO(1, "1", "Sala Premium", 40);

        when(salaValidador.validarCapacidad(salaActualizada.getCapacidad())).thenReturn(true);
        when(salaRepositorio.buscarPorId(salaActualizada.getId_sala())).thenReturn(salaOriginal);

        salaServicio.actualizarSala(salaActualizada);

        verify(salaRepositorio).actualizarSala(salaActualizada);
        verify(sillaServicio, times(10)).guardarSilla(any(SillaDTO.class));
    }

    @DisplayName("Test eliminar sala")
    @Test
    void testEliminarSala() {
        String codigo = "1";
        int id = 1;

        when(salaRepositorio.getIdSala(codigo)).thenReturn(id);

        salaServicio.eliminarSala(codigo);        
        verify(salaRepositorio).eliminarSala(id);
    }
}