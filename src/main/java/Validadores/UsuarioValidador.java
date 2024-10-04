package Validadores;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioValidador {
	
	public boolean checkContrasena(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
	
}
