package Validadores;

import java.sql.Date;

import DTOs.PeliculaDTO;

public class PeliculaValidador {
	
    public boolean validarFechaEstreno(PeliculaDTO pelicula) {
    	Date fechaActual = new Date(System.currentTimeMillis());
        Date fechaEstreno = pelicula.getFecha_estreno();  

        if (fechaEstreno == null || fechaEstreno.before(fechaActual)) {
            return false;
        }
        return true;
    }
    
    public boolean validarDuracion(PeliculaDTO pelicula) {
    	int duracion = pelicula.getDuracion_minutos();
    	
    	if(duracion <= 0 ) {
    		return false;
    	}
    	return true;
    }

}
