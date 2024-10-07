package Controladores;

import java.sql.SQLException;
import java.util.List;

import DTOs.BoletaDTO;
import DTOs.CompraDTO;
import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import Exceptions.FuncionNoEncontradaException;
import Exceptions.PeliculaNoEncontradaException;
import Servicios.BoletaServicio;
import Servicios.CompraServicio;
import Servicios.FuncionServicio;
import Servicios.PeliculaServicio;

public class ControladorHistorialCompras {

	private CompraServicio compraServicio = new CompraServicio();
	private BoletaServicio boletaServicio = new BoletaServicio();
	private PeliculaServicio peliculaServicio = new PeliculaServicio();
	private FuncionServicio funcionServicio = new FuncionServicio();
	
	public List<CompraDTO> obtenerComprasPorUsuario(int idUsuario) {
		return compraServicio.obtenerComprasPorUsuario(idUsuario);
	}
	
	public BoletaDTO obtnerInfoBoletaCompraPorUsuarioCompra(int idCompra) {
		return boletaServicio.obtenerPrimeraBoletaCompra(idCompra);
	}
	
	public PeliculaDTO obtenerPelicula(int idFuncion) throws SQLException, PeliculaNoEncontradaException, FuncionNoEncontradaException {
		FuncionDTO funcion = funcionServicio.buscarPorId(idFuncion);
		return peliculaServicio.buscarPorId(funcion.getId_pelicula());
	}
}
