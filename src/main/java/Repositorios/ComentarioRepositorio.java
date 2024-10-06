package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.ComentarioDTO;
import DTOs.FuncionDTO;
import DatabaseConfig.DatabaseConfig;

public class ComentarioRepositorio {

	public ComentarioDTO encontrarPorId(int id) {
		String query = "SELECT * FROM comentario WHERE id_comentario = ?";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new ComentarioDTO(
						resultSet.getInt("id_comentario"),
						resultSet.getString("contenido"),
						resultSet.getInt("id_usuario"),
						resultSet.getDate("fecha"),
						resultSet.getInt("id_pelicula")
						);
			} else {
				return null;
			}
		}catch (SQLException e) {
			System.out.print("ERROR AL BUSCAR ");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ComentarioDTO> obtenerComentariosPorPelicula(int idPelicula){
		 List<ComentarioDTO> comentarios = new ArrayList<>();
	        String query = "SELECT * FROM comentario WHERE id_pelicula = ?";
	        try (Connection connection = DatabaseConfig.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	             
	            preparedStatement.setInt(1, idPelicula);
	            
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    
	                    comentarios.add(new ComentarioDTO(
	                    		resultSet.getInt("id_comentario"),
	    						resultSet.getString("contenido"),
	    						resultSet.getInt("id_usuario"),
	    						resultSet.getDate("fecha"),
	    						resultSet.getInt("id_pelicula")
	                    ));
	                }
	            }
	        } catch (SQLException ex) {
	            System.err.println("ERROR AL OBTENER COMENTARIOS POR PELICULA");
	            ex.printStackTrace();
	        }
	        return comentarios;
	}
	
	public void guardarComentario(ComentarioDTO comentario) {
		String insertSQL = "INSERT INTO comentario (id_comentario, contenido, id_usuario, fecha, id_pelicula) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
             
            preparedStatement.setInt(1, comentario.getId_comentario());
            preparedStatement.setString(2, comentario.getContenido()); 
            preparedStatement.setInt(3, comentario.getIdUsuario());
            preparedStatement.setDate(4, comentario.getFecha());
            preparedStatement.setInt(5, comentario.getIdPelicula()); 
            
            preparedStatement.executeUpdate();
            System.out.println("Comentario insertado correctamente.");
        } catch (SQLException ex) {
            System.err.println("ERROR AL INSERTAR LA COMENTARIO");
            ex.printStackTrace();
        }
	}
}
