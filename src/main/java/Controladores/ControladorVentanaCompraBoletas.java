package Controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.BoletaDTO;
import DTOs.CompraDTO;
import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import DTOs.SalaDTO;
import DTOs.SillaDTO;
import DTOs.UsuarioDTO;
import Exceptions.PeliculaNoEncontradaException;
import Exceptions.SillaOcupadaException;
import Exceptions.UsuarioNoEncontradoException;
import Servicios.GenerarBoletaPDFServicio;
import Servicios.BoletaServicio;
import Servicios.CompraServicio;
import Servicios.FuncionServicio;
import Servicios.SalaServicio;
import Servicios.SillaServicio;
import Servicios.UsuarioServicio;

public class ControladorVentanaCompraBoletas {

	private CompraServicio compraServicio = new CompraServicio();
	
	private BoletaServicio boletaServicio = new BoletaServicio();
	private FuncionServicio funcionServicio = new FuncionServicio();
	private SalaServicio salaServicio = new SalaServicio();
	private SillaServicio sillaServicio = new SillaServicio();
	private UsuarioServicio usuarioServicio = new UsuarioServicio();
	
	private GenerarBoletaPDFServicio boletaPDFRepository = new GenerarBoletaPDFServicio();
	
	public CompraDTO buscarPorId(int id) {
		return compraServicio.buscarPorId(id);
	}
	
	public void agregarCompra(CompraDTO compra, int idFuncion, List<Integer> sillasSeleccionadas, double precio) throws SillaOcupadaException {
	    compraServicio.agregarCompra(compra);
	    int idCompra = compra.getIdCompra();
	    
	    List<BoletaDTO> boletasCompradas = new ArrayList<BoletaDTO>();

	    for (Integer idSilla : sillasSeleccionadas) {
	        BoletaDTO boleta = new BoletaDTO(0, idFuncion, idSilla, idCompra, precio);
	        boletaServicio.agregarBoleta(boleta);
	        boletasCompradas.add(boleta);
	    }
	    generarPDFBoletas(compra, boletasCompradas, compra.getIdUsuario());
	}

	
	public List<PeliculaDTO> obtenerPeliculasConFunciones() throws SQLException, PeliculaNoEncontradaException{
		return funcionServicio.obtenerPeliculasConFunciones();
	}
	
	public List<FuncionDTO> obtenerFuncionesPorPelicula(int id) throws SQLException, PeliculaNoEncontradaException{
		return funcionServicio.obtenerFuncionesPorPelicula(id);
	}
	
	public List<SillaDTO> obtenerSillasOcupadas(int idFuncion) {
		return sillaServicio.obtenerSillasOcupadasPorFuncion(idFuncion);
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
	
	public void generarPDFBoletas(CompraDTO compra, List<BoletaDTO> boletas, int idUsuario) {
		UsuarioDTO usuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);
		boletaPDFRepository.generarBoletaPDF(compra, boletas, usuario);
	}
}
