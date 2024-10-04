package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.SalaDTO;
import DatabaseConfig.DatabaseConfig;

public class SalaRepositorio {
	

	public SalaDTO buscarPorId(int id) {
		String query = "SELECT * FROM sala WHERE id_sala = ?";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new SalaDTO(
						resultSet.getInt("id_sala"),
						resultSet.getString("codigo_sala"),
						resultSet.getString("nombre_sala"),
						resultSet.getInt("capacidad"));
			} else {
				return null;
			}
		}catch (SQLException e) {
			System.out.print("ERROR AL BUSCAR");
			e.printStackTrace();
		}
		return null;
	}
	
	//PARA RECUPERAR LA REFERENCIA Y GUARDAR LAS SILLAS
	public int getIdSala(String codigo) {
	    String query = "SELECT id_sala FROM sala WHERE codigo_sala = ?";
	    try (Connection connection = DatabaseConfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	         
	        preparedStatement.setString(1, codigo);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            return resultSet.getInt("id_sala");
	        } else {
	            return -1; // Indicando que no se encontró
	        }
	    } catch (SQLException e) {
	        System.out.print("ERROR AL BUSCAR");
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	public void guardarSala(SalaDTO sala) {
		String codigo = sala.getCodigoSala();
		String nombre = sala.getNombreSala();
		int capacidad = sala.getCapacidad();
		
		String query = "INSERT INTO sala (codigo_sala, nombre_sala, capacidad) VALUES (?, ?, ?)";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setString(1, codigo);
			preparedStatement.setString(2, nombre);
			preparedStatement.setInt(3, capacidad);

			preparedStatement.executeUpdate();
			
			
	        System.out.println("Sala insertado correctamente.");
		}catch(SQLException e) {
			System.out.print("ERROR AL GUARDAR");
			e.printStackTrace();
		}
	}
	
	public void eliminarSala(int id) {
		String query = "DELETE FROM sala WHERE id_sala = ?";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, id);
	        
	        int rowsAffected = preparedStatement.executeUpdate();
	        
	        // Comprobar si alguna fila fue afectada (si la sala fue eliminada)
	        if (rowsAffected > 0) {
	            System.out.println("La sala con id " + id + " fue eliminada exitosamente.");
	        } else {
	            System.out.println("No se encontró ninguna sala con el id " + id + ".");
	        }
		}catch(SQLException e) {
	        System.out.println("ERROR AL ELIMINAR");
	        e.printStackTrace();

		}
	}
	
	public void actualizarSala(SalaDTO sala) {
		
		int idSala = sala.getId_sala();
		String nuevoNombre = sala.getNombreSala();
		int nuevaCapacidad = sala.getCapacidad();
		
		// Actualiza los datos de la sala
		String query = "UPDATE sala SET nombre_sala = ?, capacidad = ? WHERE id_sala = ?";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setString(1, nuevoNombre);
			preparedStatement.setInt(2, nuevaCapacidad);
			preparedStatement.setInt(3, idSala);
			
			preparedStatement.executeUpdate();
	        System.out.println("Sala editada correctamente.");
		}catch (SQLException e) {
	        System.out.println("ERROR AL EDITAR");
	        e.printStackTrace();
		}
		
	}
	
	
	
	public List<SalaDTO> getSalas() {
		String query = "SELECT * FROM sala";
		List<SalaDTO> salas = new ArrayList<>();
		
		try (Connection connection = DatabaseConfig.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(query);
		         ResultSet resultSet = preparedStatement.executeQuery()) {

		        while (resultSet.next()) {
		        	SalaDTO sala = new SalaDTO(
		                resultSet.getInt("id_sala"),        
		                resultSet.getString("codigo_sala"), 
		                resultSet.getString("nombre_sala"), 
		                resultSet.getInt("capacidad")       
		            );
		            salas.add(sala);
		        }
		    } catch (SQLException e) {
		        System.out.println("ERROR AL OBTENER LAS SALAS");
		        e.printStackTrace();
		    }
		    return salas;
		
	}
}
