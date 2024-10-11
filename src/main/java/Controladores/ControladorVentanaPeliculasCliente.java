package Controladores;

import java.sql.SQLException;
import java.util.List;

import DTOs.ComentarioDTO;
import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import DTOs.UsuarioDTO;
import Exceptions.PeliculaNoEncontradaException;
import Servicios.ComentarioServicio;
import Servicios.FuncionServicio;
import Servicios.IAService;
import Servicios.PeliculaServicio;
import Servicios.UsuarioServicio;

public class ControladorVentanaPeliculasCliente {

	PeliculaServicio peliculaServicio = new PeliculaServicio();
	ComentarioServicio comentarioServicio = new ComentarioServicio();
	UsuarioServicio usuarioServicio = new UsuarioServicio();
	FuncionServicio funcionServicio = new FuncionServicio();
	IAService iaService = new IAService();
	
	public List<PeliculaDTO> obtenerPeliculas() throws SQLException{
		return peliculaServicio.obtenerPeliculas();
	}
	
	public List<ComentarioDTO> obtenerComentariosPorPelicula(int idPelicula){
		return comentarioServicio.obtenerComentariosPorPelicula(idPelicula);
	}
	
	public boolean peliculaVista(int idPelicula, int idUsuario) {
		return usuarioServicio.peliculaVista(idPelicula, idUsuario);
	}
	
	public List<FuncionDTO> obtenerFuncionesPorPelicula(int idPelicula) throws SQLException, PeliculaNoEncontradaException{
		return funcionServicio.obtenerFuncionesPorPelicula(idPelicula);
	}
	
	public UsuarioDTO obtenerUsuarioPorId(int idUsuario) {
		return usuarioServicio.obtenerUsuarioPorId(idUsuario);
	}
	
	public void agregarComentario(ComentarioDTO comentario) {
		comentarioServicio.guardarComentario(comentario);
	}
	
	public boolean funcionYaPaso(FuncionDTO funcion) {
    	return funcionServicio.funcionYaPaso(funcion);
    }
	
	public String clasificarComentario(String comentario) {
		return iaService.clasificarComentario(comentario);
	}
}
