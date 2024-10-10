package Validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import Exceptions.CorreoNoValidoException;

public class UsuarioValidador {
	
	public boolean checkContrasena(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
	
	public boolean esCorreoValido(String email) throws CorreoNoValidoException {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if( matcher.matches()) {
        	return true;
        }
        throw new CorreoNoValidoException();
	} 
	
}
