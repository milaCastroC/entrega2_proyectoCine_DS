package Controladores;

import DTOs.UsuarioDTO;
import Exceptions.CorreoNoValidoException;
import Exceptions.UsuarioExistenteException;
import Servicios.UsuarioServicio;

public class ControladorVentanaRegistroUsuarios {
	UsuarioServicio usuarioServicio = new UsuarioServicio();
	
	public UsuarioDTO obtenerUsuarioPorId(int id) {
		UsuarioDTO usuario = null;
		usuario = usuarioServicio.obtenerUsuarioPorId(id);
		return usuario;
	}
	
	public UsuarioDTO obetenerUsuarioPorCorreo(String correo) {
		UsuarioDTO usuario = usuarioServicio.obtenerUsuarioPorCorreo(correo);
		return usuario;
	}
	
	public void guardarUsuario(UsuarioDTO usuario) throws UsuarioExistenteException, CorreoNoValidoException{
		usuarioServicio.agregarUsuario(usuario);
	}
}
