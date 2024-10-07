package Servicios;

import java.util.List;

import DTOs.BoletaDTO;
import DTOs.SillaDTO;
import Exceptions.SillaOcupadaException;
import Repositorios.BoletaRepositorio;

public class BoletaServicio {


    private BoletaRepositorio boletaRepositorio = new BoletaRepositorio();
    private SillaServicio sillaServicio = new SillaServicio();
    
    public void agregarBoleta(BoletaDTO boleta) throws SillaOcupadaException {
    	if (validarSillasDisponibles(boleta.getId_funcion(), boleta.getId_silla())) {
            boletaRepositorio.agregarBoleta(boleta);
        } else {
            throw new SillaOcupadaException();
        }
    }
    
    public boolean validarSillasDisponibles(int idFuncion, int idSillaSeleccionada) {
        List<SillaDTO> sillasOcupadas = sillaServicio.obtenerSillasOcupadasPorFuncion(idFuncion);

        for (SillaDTO sillaOcupada : sillasOcupadas) {
            if (sillaOcupada.getId_silla() == idSillaSeleccionada) {
                return false; 
            }
        }
        return true; 
   }
    
   public BoletaDTO obtenerPrimeraBoletaCompra(int idCompra) {
	   return boletaRepositorio.obtenerPrimeraBoletaCompra(idCompra);
   }
}
