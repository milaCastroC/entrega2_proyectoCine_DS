package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTOs.BoletaDTO;
import DTOs.CompraDTO;
import DatabaseConfig.DatabaseConfig;

public class BoletaRepositorio {

	public void agregarBoleta(BoletaDTO boleta) {
        String query = "INSERT INTO boleta (id_boleta, id_funcion, id_silla, id_compra, precio) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, boleta.getId_boleta()); 
            preparedStatement.setInt(2, boleta.getId_funcion());
            preparedStatement.setInt(3, boleta.getId_silla());
            preparedStatement.setInt(4, boleta.getId_compra());
            preparedStatement.setDouble(5, boleta.getPrecio());
            
            preparedStatement.executeUpdate();
            System.out.println("Boleta insertada correctamente.");
        } catch (SQLException e) {
            System.out.println("ERROR AL GUARDAR LA BOLETA");
            e.printStackTrace();
        }
    }
	
	public BoletaDTO obtenerPrimeraBoletaCompra(int idCompra) {
		String query = "SELECT * FROM boleta WHERE id_compra = ? LIMIT 1";
		try(Connection connection = DatabaseConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
				
				preparedStatement.setInt(1, idCompra);
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					return new BoletaDTO(resultSet.getInt("id_boleta"),
							resultSet.getInt("id_funcion"),
							resultSet.getInt("id_silla"),
							resultSet.getInt("id_compra"),
							resultSet.getInt("precio"));
				} else {
					return null;
				}
			}catch (SQLException e) {
				System.out.print("ERROR AL BUSCAR ");
				e.printStackTrace();
			}
			return null;
	}
}
