package Servicios;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;

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
	private CorreoServicio correoServicio = new CorreoServicio();
	
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
				
				String asunto = "¡Bienvenido a CineSoft!";
				String cuerpoMensaje = String.format("""

				    <html lang="es">
				    <meta charset="UTF-8">
				    <meta name="viewport" content="width=device-width, initial-scale=1.0">
				    <title>Bienvenida - CineSoft</title>
						<head>
					        <style>
					            @import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&display=swap');
					
					            body {
						            font-family: 'Nunito', -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif;
						            line-height: 1.6;
						            color: #343a40;
						            background-color: #f8f9fa;
						            margin: 0;
						            padding: 0;
						            font-size: 18px;
					            }
					
					            .container {
						            max-width: 600px;
						            margin: 20px auto;
						            padding: 20px;
						            background-color: #ffffff;
						            border-radius: 10px;
						            box-shadow: 0 0 40px rgba(0, 0, 0, 0.1);
					            }
					
					            h1 {
						            color: #212ea1;
						            border-bottom: 2px solid #212ea1;
						            padding-bottom: 10px;
						            font-size: 2.2em;
						            font-weight: 700;
					            }
					
					            
					            strong {
						            color: #212ea1;
						            font-weight: 700;
					            }
					
					            .greeting {
						            font-size: 1.3em;
						            font-weight: 700;
						            color: #212ea1;
						            margin-bottom: 20px;
					            }
					
					            .second-greeting{
					                font-weight: bold;
					                font-size: 1em;
					            }
					
					            ul {
						            padding-left: 20px;
					            }
					
					            li {
						            margin-bottom: 12px;
					            }
					
					            li::before {
						            content: "•";
						            color: #212ea1;
						            display: inline-block;
						            width: 1em;
						            margin-left: -1em;
						            font-weight: bold;
					            }
					
					            .footer {
						            margin-top: 30px;
						            padding-top: 15px;
						            border-top: 1px solid #343a40;
						            font-size: 0.9em;
						            color: #343a40;
						            text-align: center;
					            }
					        </style>
					    </head>
					    <body>
					        <div class="container">
					            <h1>¡Bienvenido a CineSoft, %s!</h1>
					            <p class="second-greeting">Hola %s,</p>
					            <p>Gracias por registrarte en <strong>CineSoft</strong>, tu nueva plataforma para disfrutar de una experiencia de cine única. Nos emociona que formes parte de nuestra comunidad, y estamos aquí para ofrecerte la mejor selección de películas y contenido exclusivo.</p>
					            <p>En CineSoft podrás:</p>
					            <ul>
					                <li>Explorar una vasta colección de títulos de diversos géneros.</li>
					                <li>Comentar sobre tus películas favoritas.</li>
					                <li>Tener acceso a tu registro de compras en todo momento</li>
					            </ul>
					            <p>Estamos seguros de que disfrutarás cada minuto en nuestra plataforma. Para comenzar, te invitamos a iniciar sesión y explorar lo que tenemos para ofrecerte.</p>
					            <p>Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos. ¡Estamos aquí para ayudarte!</p>
					            <p>¡Nos vemos!</p>
					            <div class="footer">
					                © 2024 CineSoft. Todos los derechos reservados.
					            </div>
					        </div>
					    </body>			    
					</html>
				    """, usuario.getNombre(), usuario.getNombre());
				correoServicio.enviarCorreoRegistro(usuario.getCorreo(), asunto, cuerpoMensaje);
			}
			
		}catch(SQLException | MessagingException ex) {
			System.out.print("ERROR AL GUARDAR ");
			ex.printStackTrace();
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
