package Controladores;

import java.sql.SQLException;
import java.util.List;

import DTOs.CompraDTO;
import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import DTOs.SalaDTO;
import DTOs.SillaDTO;
import DTOs.UsuarioDTO;
import Exceptions.PeliculaNoEncontradaException;
import Exceptions.UsuarioNoEncontradoException;
import Servicios.CompraServicio;
import Servicios.FuncionServicio;
import Servicios.SalaServicio;
import Servicios.SillaServicio;
import Servicios.UsuarioServicio;

public class ControladorVentanaCompraBoletas {

	private CompraServicio compraServicio = new CompraServicio();
	
	private FuncionServicio funcionServicio = new FuncionServicio();
	private SalaServicio salaServicio = new SalaServicio();
	private SillaServicio sillaServicio = new SillaServicio();
	private UsuarioServicio usuarioServicio = new UsuarioServicio();
	
	public CompraDTO buscarPorId(int id) {
		return compraServicio.buscarPorId(id);
	}
	
	public void guardarCompra(CompraDTO compra) {
		compraServicio.agregarCompra(compra);
	}
	
	public List<PeliculaDTO> obtenerPeliculasConFunciones() throws SQLException, PeliculaNoEncontradaException{
		return funcionServicio.obtenerPeliculasConFunciones();
	}
	
	public List<FuncionDTO> obtenerFuncionesPorPelicula(int id) throws SQLException, PeliculaNoEncontradaException{
		return funcionServicio.obtenerFuncionesPorPelicula(id);
	}
	
	public SalaDTO buscarSala(int id) {
		return salaServicio.buscarPorId(id);
	}
	
	public SillaDTO[][] obtenerMatrizSillas(SalaDTO sala) {
		return sillaServicio.obtenerMatrizSillas(sala);
	}
	
	public UsuarioDTO obtenerUsuarioPorCorreo(String correo) throws UsuarioNoEncontradoException{
		return usuarioServicio.obtenerUsuarioPorCorreo(correo);
	}
}
