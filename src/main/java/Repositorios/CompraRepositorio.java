package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.CompraDTO;
import DatabaseConfig.DatabaseConfig;

public class CompraRepositorio {
	
	public CompraDTO encontrarPorId(int id) {
		String query = "SELECT * FROM compra WHERE id_compra = ?";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new CompraDTO(resultSet.getInt("id_compra"),
						resultSet.getDate("fecha"),
						resultSet.getInt("id_usuario"),
						resultSet.getInt("cantidad_boletas"));
			} else {
				return null;
			}
		}catch (SQLException e) {
			System.out.print("ERROR AL BUSCAR ");
			e.printStackTrace();
		}
		return null;
	}
	
	public void guardarCompra(CompraDTO compra) {
		
		String query = "INSERT INTO compra (id_compra, fecha, id_usuario, cantidad_boletas) VALUES (?, ?, ?, ?)";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, compra.getIdCompra());
			preparedStatement.setDate(2, compra.getFecha());
			if(compra.getIdUsuario() != -1) {
				preparedStatement.setInt(3, compra.getIdUsuario());
			} else {
		        preparedStatement.setNull(3, java.sql.Types.INTEGER); // Si es null, insertar NULL
			}
			
			preparedStatement.setInt(4, compra.getCantidad());

			preparedStatement.executeUpdate();
	        System.out.println("Compra insertado correctamente.");
		}catch(SQLException e) {
			System.out.print("ERROR AL GUARDAR");
			e.printStackTrace();
		}
	}
	
	public List<CompraDTO> obtenerComprasPorUsuario(int idUsuario) {
		List<CompraDTO> compras = new ArrayList<>();
        String query = "SELECT * FROM compra WHERE id_usuario = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, idUsuario);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    
                    compras.add(new CompraDTO(
                            resultSet.getInt("id_compra"),
                            resultSet.getDate("fecha"),
                            resultSet.getInt("id_usuario"),
                            resultSet.getInt("cantidad_boletas")
                    ));
                }
            }
        } catch (SQLException ex) {
            System.err.println("ERROR AL OBTENER COMPRAS POR USUARIO");
            ex.printStackTrace();
        }
        return compras;
	}
}
