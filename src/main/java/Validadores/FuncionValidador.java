package Validadores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import DTOs.FuncionDTO;

public class FuncionValidador {

    public boolean validarFechaFuncion(FuncionDTO funcion) {
        LocalDateTime fechaHoraFuncion = LocalDateTime.of(funcion.getFecha().toLocalDate(), funcion.getHora().toLocalTime());
        if(fechaHoraFuncion.isBefore(LocalDateTime.now())) {
        	return false;
        }
        return true;
    }

  public boolean validarCruceFunciones(FuncionDTO nuevaFuncion, int duracionPelicula, List<FuncionDTO> funcionesExistentes) {

	  LocalTime horaInicioNueva = nuevaFuncion.getHora().toLocalTime();
	  LocalTime horaFinNueva = horaInicioNueva.plusMinutes(duracionPelicula);
	  String fechaNuevaFuncion = String.valueOf( nuevaFuncion.getFecha());
	  
	  for (FuncionDTO funcion : funcionesExistentes) {
		  String fechaFuncionExistente = String.valueOf(funcion.getFecha());

		  if (funcion.getId_sala() == nuevaFuncion.getId_sala() && fechaNuevaFuncion.equals(fechaFuncionExistente) ) {
	          LocalTime horaInicioExistente = funcion.getHora().toLocalTime();
	          LocalTime horaFinExistente = horaInicioExistente.plusMinutes(duracionPelicula + 59); //Una hora para barrer la sala
	
	          if (!(horaFinNueva.isBefore(horaInicioExistente) || horaInicioNueva.isAfter(horaFinExistente)) || horaInicioNueva.equals(horaFinExistente)) {
	              return false; 
	          }
	      }
	  }
	  return true;
  }
  
  	public boolean funcionYaPaso(FuncionDTO funcion) {
	    LocalDateTime fechaHoraFuncion = LocalDateTime.of(funcion.getFecha().toLocalDate(), funcion.getHora().toLocalTime());
	    return fechaHoraFuncion.isBefore(LocalDateTime.now());
	}
     
  	public boolean validarFechaFuncion(FuncionDTO funcion, LocalDate fechaEstreno) {
        LocalDate fechaFuncion = funcion.getFecha().toLocalDate();
        return !fechaFuncion.isBefore(fechaEstreno);
    }

}

