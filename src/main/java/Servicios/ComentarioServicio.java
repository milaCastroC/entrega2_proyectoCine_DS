package Servicios;

import java.util.List;

import DTOs.ComentarioDTO;
import IAConfig.IAConfig;
import Repositorios.ComentarioRepositorio;

public class ComentarioServicio {
	
	private ComentarioRepositorio comentarioRepositorio = new ComentarioRepositorio();
	
	public ComentarioDTO buscarComentarioPorId(int id) {
		return comentarioRepositorio.encontrarPorId(id);
	}
	
	public List<ComentarioDTO> obtenerComentariosPorPelicula(int idPelicula){
		return comentarioRepositorio.obtenerComentariosPorPelicula(idPelicula);
	}
	
	public void guardarComentario(ComentarioDTO comentario) {
		comentarioRepositorio.guardarComentario(comentario);
	}
	
}
