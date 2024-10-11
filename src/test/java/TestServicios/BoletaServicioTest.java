package TestServicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DTOs.BoletaDTO;
import DTOs.SillaDTO;
import Exceptions.SillaOcupadaException;
import Repositorios.BoletaRepositorio;
import Servicios.BoletaServicio;
import Servicios.SillaServicio;

class BoletaServicioTest {


    @Mock
    private BoletaRepositorio boletaRepositorio;

    @Mock
    private SillaServicio sillaServicio;

    @InjectMocks
    private BoletaServicio boletaServicio;
    
    @BeforeEach
    public void antes() {
        MockitoAnnotations.openMocks(this);

    }

    @DisplayName("Test agregar boleta")
    @Test
    public void testAgregarBoletaExitoso() throws SillaOcupadaException {
        BoletaDTO boleta = new BoletaDTO(1, 1, 1, 1, 10.0);
        when(sillaServicio.obtenerSillasOcupadasPorFuncion(1)).thenReturn(Arrays.asList());

        boletaServicio.agregarBoleta(boleta);

        verify(boletaRepositorio).agregarBoleta(boleta);
    }

    @DisplayName("Test agregar boleta en silla ocupada")
    @Test
    public void testAgregarBoletaSillaOcupada() throws SillaOcupadaException {
        BoletaDTO boleta = new BoletaDTO(1, 1, 1, 1, 10.0);
        List<SillaDTO> sillasOcupadas = Arrays.asList(new SillaDTO(1, 1, 1));
        when(sillaServicio.obtenerSillasOcupadasPorFuncion(1)).thenReturn(sillasOcupadas);

        assertThrows(SillaOcupadaException.class, ()->{
            boletaServicio.agregarBoleta(boleta);
        },"SillaOcupadaException se lanza cuando la silla esta ocupada");
    }

    @DisplayName("Test validar sillas disponibles")
    @Test
    public void testValidarSillasDisponibles() {
        List<SillaDTO> sillasOcupadas = new ArrayList<SillaDTO>();
        sillasOcupadas.add(new SillaDTO(2, 1, 1));
        when(sillaServicio.obtenerSillasOcupadasPorFuncion(1)).thenReturn(sillasOcupadas);

        boolean resultado = boletaServicio.validarSillasDisponibles(1, 1);

        assertTrue(resultado);
    }

    @DisplayName("Test validar sillas disponibles - silla ocupada")
    @Test
    public void testValidarSillasDisponiblesSillaOcupada() {
        List<SillaDTO> sillasOcupadas = Arrays.asList(new SillaDTO(1, 1, 1));
        when(sillaServicio.obtenerSillasOcupadasPorFuncion(1)).thenReturn(sillasOcupadas);

        boolean resultado = boletaServicio.validarSillasDisponibles(1, 1);

        assertFalse(resultado);
    }

    @DisplayName("Test obtener primera boleta de una compra")
    @Test
    public void testObtenerPrimeraBoletaCompra() {
        BoletaDTO boletaEsperada = new BoletaDTO(1, 1, 1, 1, 10.0);
        when(boletaRepositorio.obtenerPrimeraBoletaCompra(1)).thenReturn(boletaEsperada);

        BoletaDTO resultado = boletaServicio.obtenerPrimeraBoletaCompra(1);

        assertEquals(boletaEsperada, resultado);
    }

}
