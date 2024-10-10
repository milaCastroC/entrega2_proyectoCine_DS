package TestValidadores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import Exceptions.CorreoNoValidoException;
import Exceptions.UsuarioExistenteException;
import Validadores.UsuarioValidador;

class UsuarioValidadorTest {
	
	private UsuarioValidador usuarioValidador;
	
	@BeforeEach
	void antes() {
		usuarioValidador = new UsuarioValidador();
	}

	@Test
	@DisplayName("Test check contrasena correcta")
	void testCheckContrasena() {
		String contrasena = BCrypt.hashpw("123", BCrypt.gensalt());
		boolean resultado = usuarioValidador.checkContrasena("123", contrasena);
		assertTrue(resultado);
	}
	
	@Test
	@DisplayName("Test correo valido")
	void testCorreoValido() throws CorreoNoValidoException {
		boolean resultado = usuarioValidador.esCorreoValido("Pepito@cine.com");
		assertTrue(resultado);
	}
	
	@Test
	@DisplayName("Test correo no valido")
	void testCorreoNoValido() throws CorreoNoValidoException {
		assertThrows(CorreoNoValidoException.class, () -> {
        	usuarioValidador.esCorreoValido("Pepito.com");
        	}, "CorreoNoValidoException es lanzada cuando el correo no cumple los requisitos necesarios");
    
	}

}
