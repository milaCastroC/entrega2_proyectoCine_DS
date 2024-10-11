package TestValidadores;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import DTOs.PeliculaDTO;
import Validadores.PeliculaValidador;

class PeliculaValidadorTest {

    private PeliculaValidador peliculaValidador;
    private PeliculaDTO pelicula;

    @BeforeEach
    void antes() {
        peliculaValidador = new PeliculaValidador();
        pelicula = new PeliculaDTO(1, "1", "Mulán", null, "Disney", 120, "Aventura", "Me encanta");
    }

    @DisplayName("Test Validar Fecha de Estreno")
    @Test
    void testValidarFechaEstreno() {
        pelicula.setFecha_estreno(Date.valueOf("2025-01-01"));

        assertTrue(peliculaValidador.validarFechaEstreno(pelicula));
    }

    @DisplayName("Test Validar Fecha Estreno No Válida")
    @Test
    void testValidarFechaEstrenoInvalida() {
        pelicula.setFecha_estreno(Date.valueOf("2023-01-01")); // Fecha pasada

        assertFalse(peliculaValidador.validarFechaEstreno(pelicula));
    }

    

    @DisplayName("Test validarDuracion")
    @Test
    void testValidarDuracionValida() {
        pelicula.setDuracion_minutos(120); 

        assertTrue(peliculaValidador.validarDuracion(pelicula));
    }

    @DisplayName("Test Validar Duracion No Válida")
    @Test
    void testValidarDuracionInvalida() {
        pelicula.setDuracion_minutos(0); 

        assertFalse(peliculaValidador.validarDuracion(pelicula));
    }

}
