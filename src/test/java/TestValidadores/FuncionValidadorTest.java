package TestValidadores;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DTOs.FuncionDTO;
import Validadores.FuncionValidador;

class FuncionValidadorTest {

	private FuncionValidador funcionValidador;

    @BeforeEach
    void antes() {
        funcionValidador = new FuncionValidador();
    }
    
    // Prueba para validar la fecha de la función (función ya pasada)
    @Test
    void testValidarFechaFuncion_FuncionPasada() {
        
        FuncionDTO funcion = mock(FuncionDTO.class);// Creamos un mock de FuncionDTO
        
        // Simulamos que la fecha de la función es un día anterior a la fecha actual
        Date fechaPasada = Date.valueOf(LocalDate.now().minusDays(1)); 
        Time horaPasada = Time.valueOf(LocalTime.now().minusHours(1));
        
        // Definimos que los métodos del mock retornen estos valores
        when(funcion.getFecha()).thenReturn(fechaPasada);
        when(funcion.getHora()).thenReturn(horaPasada);

        // Llamamos al método 
        boolean resultado = funcionValidador.validarFechaFuncion(funcion);

        // Verificamos que el resultado se false
        assertFalse(resultado);
    }
    
    // Prueba para validar la fecha de la función (función futura)
    @Test
    void testValidarFechaFuncion_FuncionFutura() {
       
        FuncionDTO funcion = mock(FuncionDTO.class);// Creamos un mock de FuncionDTO 
        
        // Simulamos que la fecha de la función es un día en el futuro
        Date fechaFutura = Date.valueOf(LocalDate.now().plusDays(1)); 
        Time horaFutura = Time.valueOf(LocalTime.now().plusHours(1));

        when(funcion.getFecha()).thenReturn(fechaFutura);
        when(funcion.getHora()).thenReturn(horaFutura);

        // Llamamos al método
        boolean resultado = funcionValidador.validarFechaFuncion(funcion);

        // Verificamos que el resultado sea true 
        assertTrue(resultado);
    }

    
    // Prueba para validar cruce de funciones (sin cruce de horario)
    @Test
    void testValidarCruceFunciones_SinCruce() {
        // Simulamos una nueva función que empieza a las 14:00
        FuncionDTO nuevaFuncion = mock(FuncionDTO.class);
        
        Date fechaNueva = Date.valueOf(LocalDate.now());
        Time horaNueva = Time.valueOf(LocalTime.of(14, 0));

        when(nuevaFuncion.getFecha()).thenReturn(fechaNueva);
        when(nuevaFuncion.getHora()).thenReturn(horaNueva);
        when(nuevaFuncion.getId_sala()).thenReturn(1);
        
        // Simulamos una función existente en la misma sala que empieza a las 10:00
        FuncionDTO funcionExistente = mock(FuncionDTO.class);
        
        Date fechaExistente = Date.valueOf(LocalDate.now());
        Time horaExistente = Time.valueOf(LocalTime.of(10, 0));

        when(funcionExistente.getFecha()).thenReturn(fechaExistente);
        when(funcionExistente.getHora()).thenReturn(horaExistente);
        when(funcionExistente.getId_sala()).thenReturn(1);
        
        // Creamos una lista de funciones existentes 
        List<FuncionDTO> funcionesExistentes = Arrays.asList(funcionExistente);

        // Llamamos al método que queremos probar
        boolean resultado = funcionValidador.validarCruceFunciones(nuevaFuncion, 120, funcionesExistentes);

        // Verificamos que no haya cruce, por lo que el resultado debe ser true
        assertTrue(resultado);
    }
    
 // Prueba para validar cruce de funciones (con cruce de horario)
    @Test
    void testValidarCruceFunciones_ConCruce() {
        // Simulamos una nueva función que empieza a las 12:00
        FuncionDTO nuevaFuncion = mock(FuncionDTO.class);
        
        Date fechaNueva = Date.valueOf(LocalDate.now());
        Time horaNueva = Time.valueOf(LocalTime.of(12, 0));

        when(nuevaFuncion.getFecha()).thenReturn(fechaNueva);
        when(nuevaFuncion.getHora()).thenReturn(horaNueva);
        when(nuevaFuncion.getId_sala()).thenReturn(1);

        // Simulamos una función existente que empieza a las 11:00
        FuncionDTO funcionExistente = mock(FuncionDTO.class);
        
        Date fechaExistente = Date.valueOf(LocalDate.now());
        Time horaExistente = Time.valueOf(LocalTime.of(11, 0));

        when(funcionExistente.getFecha()).thenReturn(fechaExistente);
        when(funcionExistente.getHora()).thenReturn(horaExistente);
        when(funcionExistente.getId_sala()).thenReturn(1);


        // Creamos una lista de funciones existentes
        List<FuncionDTO> funcionesExistentes = Arrays.asList(funcionExistente);

        // Llamamos al método que queremos probar
        boolean resultado = funcionValidador.validarCruceFunciones(nuevaFuncion, 120, funcionesExistentes);

        // Verificamos que sí hay cruce, por lo que el resultado debe ser false
        assertFalse(resultado);
    }
    

}
