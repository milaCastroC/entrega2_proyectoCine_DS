package TestServicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DTOs.UsuarioDTO;
import Exceptions.CorreoContrasenaIncorrectosException;
import Exceptions.CorreoNoValidoException;
import Exceptions.UsuarioExistenteException;
import Exceptions.UsuarioNoEncontradoException;
import Repositorios.UsuarioRepositorio;
import Servicios.CorreoServicio;
import Servicios.UsuarioServicio;

class UsuarioServicioTest {

	@Mock
    private UsuarioRepositorio usuarioRepositorioMock;
	
	@Mock
    private UsuarioServicio usuarioValidadorMock;
	
	@Mock
    private CorreoServicio correoServicioMock;
	
	
	
    @InjectMocks
    private UsuarioServicio usuarioServicio;

    private UsuarioDTO usuario;

    @BeforeEach
    void antes() {
        usuario = new UsuarioDTO(1, "Pepito@cine.com", "123", UsuarioDTO.ADMINISTRADOR, "Pepito", "123");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test obtener usuario por id")
    void testObtenerUsuarioPorId() throws SQLException {
        when(usuarioRepositorioMock.buscarUsuarioPorId(1)).thenReturn(usuario);
        UsuarioDTO resultado = usuarioServicio.obtenerUsuarioPorId(1);
        assertEquals(usuario, resultado);
    }
    
    @Test
    @DisplayName("Test obtener usuario por correo")
    void testObtenerUsuarioPorCorreo() throws UsuarioNoEncontradoException {
        when(usuarioRepositorioMock.buscarUsuarioPorCorreo("Pepito@cine.com")).thenReturn(usuario);
        UsuarioDTO resultado = usuarioServicio.obtenerUsuarioPorCorreo("Pepito@cine.com");
        assertEquals(usuario, resultado);
    }

    @Test
    @DisplayName("Test usuario no encontrado por correo")
    void testObtenerUsuarioPorCorreoNoEncontrado() {
        when(usuarioRepositorioMock.buscarUsuarioPorCorreo("noEsPepito@cine.com")).thenReturn(null);
        
        assertThrows(UsuarioNoEncontradoException.class, () -> {
        	usuarioServicio.obtenerUsuarioPorCorreo("noExiste@cine.com");
        	}, "UsuarioNoEncontradoException debe lanzarse si el usuario no fue encontrado");
    }

    @Test
    @DisplayName("Test agregar usuario")
    void testAgregarUsuario() throws UsuarioExistenteException, CorreoNoValidoException, SQLException, MessagingException {
        when(usuarioRepositorioMock.buscarUsuarioPorCorreo("Pepito@cine.com")).thenReturn(null);
        usuarioServicio.agregarUsuario(usuario);

        verify(correoServicioMock).enviarCorreoRegistro(usuario); //Verifica que se envió el correo
    } 
    
    @Test
    @DisplayName("Test agregar usuario ya existente")
    void testAgregarUsuario_UsuarioExistente() throws SQLException {
        when(usuarioRepositorioMock.buscarUsuarioPorCorreo("Pepito@cine.com")).thenReturn(usuario);
        
        assertThrows(UsuarioExistenteException.class, () -> {
        	usuarioServicio.agregarUsuario(usuario);
        	}, "UsuarioExistenteException es lanzada cuando se trata de agregar un usuario ya registrado");
    }

    @Test
    @DisplayName("Test usuario valido")
    void testUsuarioValido() throws UsuarioExistenteException {
        when(usuarioRepositorioMock.buscarUsuarioPorCorreo("nuevoUsuario@cine.com")).thenReturn(null);
        assertTrue(usuarioServicio.usuarioValido("nuevoUsuario@cine.com"));
    }

    @Test
    @DisplayName("Test usuario no valido (Ya existente)")
    void testUsuarioValido_UsuarioExistente() throws UsuarioExistenteException {
        when(usuarioRepositorioMock.buscarUsuarioPorCorreo("Pepito@cine.com")).thenReturn(usuario);
        
        assertThrows(UsuarioExistenteException.class, () -> {
        	usuarioServicio.usuarioValido("Pepito@cine.com");
        	},"UsuarioExistenteException se lanza cuando el usuario ya esta registrado");
    }

//    @Test
//    @DisplayName("Test hash password")
//    void testHashPassword() {
//        String contrasena = "Contrasena-123";
//        String hashedPassword = usuarioServicio.hashPassword(contrasena);
//
//        // Verifica que el hash generado coincide con la contraseña original
//        assertTrue(BCrypt.checkpw(contrasena, hashedPassword));
//    }
//
//    @Test
//	@DisplayName("Test check contraseña")
//	void testCheckContrasena() throws CorreoContrasenaIncorrectosException {
//	    when(usuarioRepositorio.buscarUsuarioPorCorreo("Pepito@cine.com")).thenReturn(usuario);
//	    String hashedPassword = usuarioServicio.hashPassword("123");
//	    when(usuarioValidador.checkContrasena("123", hashedPassword)).thenReturn(true);
//	    
//	    assertTrue(usuarioServicio.checkContrasena("Pepito@cine.com", "123"));
//	}
// 
//	@Test
//	@DisplayName("Test check contraseña incorrecta")
//	void testCheckContrasena_Incorrecta() throws CorreoContrasenaIncorrectosException {
//		when(usuarioRepositorio.buscarUsuarioPorCorreo("Pepito@cine.com")).thenReturn(usuario);
//		when(usuarioValidador.checkContrasena("1234", usuario.getContrasena())).thenReturn(false);
//
//		assertThrows(CorreoContrasenaIncorrectosException.class, () -> {
//			usuarioServicio.checkContrasena("Pepito@cine.com", "1234");
//		}, "CorreoContrasenaIncorrectosException se lanza cuando el usuario o la contraseña es incorrect");
//	}
//	  
//	

    
	@Test
	@DisplayName("Test película vista")
	void testPeliculaVista() {
		List<Integer> idPeliculas = new ArrayList<>();
		idPeliculas.add(0);
		idPeliculas.add(1);
		idPeliculas.add(2);

		when(usuarioRepositorioMock.obtenerPeliculasVistasPorUsuario(1)).thenReturn(idPeliculas);
		assertTrue(usuarioServicio.peliculaVista(1, 1));
	}

    @Test
    @DisplayName("Test película no vista")
    void testPeliculaNoVista() {
    	List<Integer> idPeliculas = new ArrayList<>();
    	idPeliculas.add(0);
    	idPeliculas.add(1);
    	idPeliculas.add(2);
    	
        when(usuarioRepositorioMock.obtenerPeliculasVistasPorUsuario(1)).thenReturn(idPeliculas);
        assertFalse(usuarioServicio.peliculaVista(5, 1));
    }

    
}
