package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import DTOs.UsuarioDTO;
import DatabaseConfig.DatabaseConfig;

public class UsuarioRepositorio {
	
	public UsuarioDTO buscarUsuarioPorId(int id) throws SQLException {
	    String query = "SELECT * FROM usuario WHERE id_usuario = ?";
	    try (Connection connection = DatabaseConfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        
	        preparedStatement.setInt(1, id);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                String rol = "";
	                int idRol = resultSet.getInt("id_rol");
	                if (idRol == 1) {
	                    rol = UsuarioDTO.ADMINISTRADOR;
	                } else if (idRol == 2) {
	                    rol = UsuarioDTO.CLIENTE;
	                }
	                
	                return new UsuarioDTO(
	                        resultSet.getInt("id_usuario"), 
	                        resultSet.getString("correo"), 
	                        resultSet.getString("contrasena"),
	                        rol,
	                        resultSet.getString("nombre"),
	                        resultSet.getString("telefono")
	                );
	            } else {
	                return null; 
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("NO SE ENCONTRÓ EL USUARIO (ID)");
	        e.printStackTrace();
	    }
	    return null;
	}

	
	public UsuarioDTO buscarUsuarioPorCorreo(String correo) {
		correo.toLowerCase();
	    String query = "SELECT * FROM usuario WHERE correo = ?";
	    try (Connection connection = DatabaseConfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        
	        preparedStatement.setString(1, correo);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                String rol = "";
	                int idRol = resultSet.getInt("id_rol");
	                if (idRol == 1) {
	                    rol = UsuarioDTO.ADMINISTRADOR;
	                } else if (idRol == 2) {
	                    rol = UsuarioDTO.CLIENTE;
	                }
	                
	                return new UsuarioDTO(
	                        resultSet.getInt("id_usuario"), 
	                        resultSet.getString("correo"), 
	                        resultSet.getString("contrasena"),
	                        rol,
	                        resultSet.getString("nombre"),
	                        resultSet.getString("telefono")
	                );
	            } else {
	                return null; 
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("NO SE ENCONTRÓ EL USUARIO (CORREO)");
	        e.printStackTrace();
	    }
	    return null; 
	}
	
	public int obtenerIdPorCorreo(String correo) {
		String query = "SELECT id_usuario FROM usuario WHERE correo = ?";
	    try (Connection connection = DatabaseConfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	         
	        preparedStatement.setString(1, correo);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            return resultSet.getInt("id_usuario");
	        } else {
	            return -1; // Indicando que no se encontró
	        }
	    } catch (SQLException e) {
	        System.out.print("ERROR AL BUSCAR");
	        e.printStackTrace();
	    }
	    return -1;
	}


	public void agregarUsuario(UsuarioDTO usuario) throws SQLException {
	    String correo = usuario.getCorreo();
	    String nombre = usuario.getNombre();
	    String telefono = usuario.getTelefono();
	    int rol;

	    // Validar el rol del usuario
	    if (usuario.getRol().equals(UsuarioDTO.ADMINISTRADOR)) {
	        rol = 1;
	    } else if (usuario.getRol().equals(UsuarioDTO.CLIENTE)) {
	        rol = 2;
	    } else {
	        throw new IllegalArgumentException("Rol de usuario no válido.");
	    }

	    String contrasena = usuario.getContrasena();
	    String hashedContrasena = hashPassword(contrasena);

	    String insertSQL = "INSERT INTO usuario (correo, nombre, telefono, id_rol, contrasena) VALUES (?, ?, ?, ?, ?)";
	    try (Connection connection = DatabaseConfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
	        
	        preparedStatement.setString(1, correo);
	        preparedStatement.setString(2, nombre);
	        preparedStatement.setString(3, telefono);
	        preparedStatement.setInt(4, rol);
	        preparedStatement.setString(5, hashedContrasena);
	        
	        preparedStatement.executeUpdate();
	        System.out.println("Usuario insertado correctamente.");
	    } catch (SQLException e) {
	        System.err.println("Error al insertar el usuario: " + e.getMessage());
	        throw e; // Lanzar la excepción para que el llamador pueda manejarla
	    }
	}


	 public String hashPassword(String contrasena) {
			return BCrypt.hashpw(contrasena, BCrypt.gensalt());
	 }
	 
	 
	 public List<Integer> obtenerPeliculasVistasPorUsuario(int idUsuario) {
	        List<Integer> peliculasVistas = new ArrayList<>();
	        String query = "SELECT DISTINCT f.id_pelicula " +
	                       "FROM usuario u " +
	                       "JOIN compra c ON u.id_usuario = c.id_usuario " +
	                       "JOIN boleta b ON c.id_compra = b.id_compra " +
	                       "JOIN funcion f ON b.id_funcion = f.id_funcion " +
	                       "WHERE u.id_usuario = ?";

	        try (Connection connection = DatabaseConfig.getConnection();
	        	PreparedStatement ps = connection.prepareStatement(query)) {
	            
	        	ps.setInt(1, idUsuario);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    peliculasVistas.add(rs.getInt("id_pelicula"));
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Manejo de excepciones apropiado según los requerimientos
	        }
	        return peliculasVistas;
	    }
}
