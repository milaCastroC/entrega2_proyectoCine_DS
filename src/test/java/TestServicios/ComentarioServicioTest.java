package TestServicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DTOs.ComentarioDTO;
import Repositorios.ComentarioRepositorio;
import Servicios.ComentarioServicio;

class ComentarioServicioTest {

    @Mock
    private ComentarioRepositorio comentarioRepositorio;

    @InjectMocks
    private ComentarioServicio comentarioServicio;

    @BeforeEach
    void antes() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Test buscar comentario por id")
    @Test
    void testBuscarComentarioPorId() {
        int id = 1;
        ComentarioDTO comentarioEsperado = new ComentarioDTO(1, "Buen comentario", 1, new Date(System.currentTimeMillis()), 1, "Positivo");
        when(comentarioRepositorio.encontrarPorId(id)).thenReturn(comentarioEsperado);

        ComentarioDTO comentarioResultado = comentarioServicio.buscarComentarioPorId(id);

        assertEquals(comentarioEsperado, comentarioResultado);
    }

    @DisplayName("Test obtener comentarios por película")
    @Test
    void testObtenerComentariosPorPelicula() {
        int idPelicula = 1;
        List<ComentarioDTO> comentariosEsperados = new ArrayList<ComentarioDTO>();
        comentariosEsperados.add(new ComentarioDTO(1, "Comentario 1", 1, new Date(System.currentTimeMillis()), idPelicula, "Positivo"));
        comentariosEsperados.add(new ComentarioDTO(2, "Comentario 2", 2, new Date(System.currentTimeMillis()), idPelicula, "Negativo"));
        
        when(comentarioRepositorio.obtenerComentariosPorPelicula(idPelicula)).thenReturn(comentariosEsperados);

        List<ComentarioDTO> comentariosResultado = comentarioServicio.obtenerComentariosPorPelicula(idPelicula);

        assertEquals(comentariosEsperados, comentariosResultado);
    }

    @DisplayName("Test guardar comentario")
    @Test
    void testGuardarComentario() {
        ComentarioDTO nuevoComentario = new ComentarioDTO(3, "Nuevo comentario", 3, new Date(System.currentTimeMillis()), 2, "neutral");

        comentarioServicio.guardarComentario(nuevoComentario);

        verify(comentarioRepositorio).guardarComentario(nuevoComentario);
    }
}
