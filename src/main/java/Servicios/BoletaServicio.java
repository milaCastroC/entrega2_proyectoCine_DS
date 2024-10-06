package Servicios;

import DTOs.BoletaDTO;
import Repositorios.BoletaRepositorio;

public class BoletaServicio {


    BoletaRepositorio boletaRepositorio = new BoletaRepositorio();
    
    public void agregarBoleta(BoletaDTO boleta) {
        boletaRepositorio.agregarBoleta(boleta);
    }
}
