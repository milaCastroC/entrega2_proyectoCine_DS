package Validadores;

public class SalaValidador {
	public boolean validarCapacidad(int capacidad) {
		return capacidad>= 10 && capacidad<= 50;
	}
}
