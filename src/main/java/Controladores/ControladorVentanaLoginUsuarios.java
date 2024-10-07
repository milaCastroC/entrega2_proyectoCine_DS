package Controladores;

import DTOs.UsuarioDTO;
import Exceptions.CorreoContrasenaIncorrectosException;
import Servicios.UsuarioServicio;

public class ControladorVentanaLoginUsuarios {
	
    private UsuarioServicio usuarioServicio = new UsuarioServicio();
	
	public ControladorVentanaLoginUsuarios() {
		UsuarioDTO adminExistente = usuarioServicio.obtenerUsuarioPorId(1);
		if(adminExistente == null) {
			crearAdminGeneral();
		}
	}
	
    
    private void crearAdminGeneral() {
    	UsuarioDTO adminGeneral = new UsuarioDTO("juancho@cinesoft.com", "123", UsuarioDTO.ADMINISTRADOR, "juancho", "123445");
    	try {
    		usuarioServicio.agregarUsuario(adminGeneral);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public UsuarioDTO getUsuarioByCorreo(String correo) {
    	return usuarioServicio.obtenerUsuarioPorCorreo(correo);
    }
    
    public boolean validarContrasena(String correo, String contrasena) throws CorreoContrasenaIncorrectosException {
    	return usuarioServicio.checkContrasena(correo, contrasena);
    }
}
