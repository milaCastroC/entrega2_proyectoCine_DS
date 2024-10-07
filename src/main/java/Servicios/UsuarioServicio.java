package Servicios;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import DTOs.UsuarioDTO;
import Exceptions.CorreoContrasenaIncorrectosException;
import Exceptions.CorreoNoValidoException;
import Exceptions.UsuarioExistenteException;
import Exceptions.UsuarioNoEncontradoException;
import Repositorios.UsuarioRepositorio;
import Validadores.UsuarioValidador;

public class UsuarioServicio {
	
	private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
	private UsuarioValidador usuarioValidador = new UsuarioValidador();
	
	public UsuarioDTO obtenerUsuarioPorId(int id){
		UsuarioDTO usuario = null;
		try {
			usuario = usuarioRepositorio.buscarUsuarioPorId(id);
		} catch (SQLException e) {
			System.out.print("NO SE ENCONTRÓ EL USUARIO ");
			e.printStackTrace();
		}
		return usuario;
	}
	
	public UsuarioDTO obtenerUsuarioPorCorreo(String correo) throws UsuarioNoEncontradoException{
		UsuarioDTO usuario = usuarioRepositorio.buscarUsuarioPorCorreo(correo);
		if(usuario != null) {
			return usuario;
		} else {
			throw new UsuarioNoEncontradoException();
		}
		
	}
	
	public void agregarUsuario(UsuarioDTO usuario) throws UsuarioExistenteException, CorreoNoValidoException {
		try {
			
			if(usuarioValido(usuario.getCorreo()) && usuarioValidador.esCorreoValido(usuario.getCorreo())){
				usuarioRepositorio.agregarUsuario(usuario);
			}
			
		}catch(SQLException e) {
			System.out.print("ERROR AL GUARDAR ");
			e.printStackTrace();
		}
		
	}
	
	public boolean usuarioValido(String correo) throws UsuarioExistenteException {
			UsuarioDTO usuario = usuarioRepositorio.buscarUsuarioPorCorreo(correo);
			if(usuario == null) {
				return true; 
			}
			throw new UsuarioExistenteException();	
	}
	
	public boolean checkContrasena(String correo, String contrasenaIngresada) throws CorreoContrasenaIncorrectosException{
		UsuarioDTO usuario = usuarioRepositorio.buscarUsuarioPorCorreo(correo);
		if(usuario != null) {
			if(usuarioValidador.checkContrasena(contrasenaIngresada, usuario.getContrasena())) {
				return true;
			} else {
				throw new CorreoContrasenaIncorrectosException();
			}
		}
		throw new CorreoContrasenaIncorrectosException();
	}
	
	public String hashPassword(String contrasena) {
		return BCrypt.hashpw(contrasena, BCrypt.gensalt());
	}
	
	public boolean peliculaVista(int idPelicula, int idUsuario) {
	    List<Integer> peliculasVistas = usuarioRepositorio.obtenerPeliculasVistasPorUsuario(idUsuario);
	    return peliculasVistas.contains(idPelicula);
	}
}
