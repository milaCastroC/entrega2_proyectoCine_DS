package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DTOs.SalaDTO;
import DTOs.SillaDTO;
import DatabaseConfig.DatabaseConfig;

public class SillaRepositorio {
	
	public SillaDTO buscarSillaPorId(int id) {
		String query = "SELECT * FROM sala WHERE id_silla = ?";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new SillaDTO(
						resultSet.getInt("id_silla"),
						resultSet.getInt("numero"),
						resultSet.getInt("idSala")
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
	
	public void guardarSilla(SillaDTO silla) {
		int id = silla.getId_silla();
		int numero = silla.getIdentificador();
		int id_sala = silla.getIdSala();
		
		String query = "INSERT INTO silla (id_silla, numero, id_sala) VALUES (?, ?, ?)";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, numero);
			preparedStatement.setInt(3, id_sala);

			preparedStatement.executeUpdate();
	        System.out.println("Silla insertado correctamente.");
		}catch(SQLException e) {
			System.out.print("ERROR AL GUARDAR");
			e.printStackTrace();
		}
	}
	
	public void eliminarSillaPorNumSala(int numero, int idSala) {
		String query = "DELETE FROM silla WHERE numero = ? AND id_sala = ?";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, numero);
			preparedStatement.setInt(2, idSala);

			preparedStatement.executeUpdate();
	        System.out.println("Silla eliminada correctamente.");
		}catch(SQLException e) {
			System.out.print("ERROR AL ELIMINAR SILLA");
			e.printStackTrace();
		}
	}
	
	public SillaDTO[][] obtenerMatrizSillas(SalaDTO sala) {
        SillaDTO[][] matrizSillas = generarMatriz(sala.getCapacidad());
        
        String query = "SELECT * FROM silla WHERE id_sala = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, sala.getId_sala());
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                
            	//llenar matriz
            	int fila = 0;
                int columna = 0;
                int cantColumnas = 8;
                
                while (resultSet.next()) {
                    
                    SillaDTO silla = new SillaDTO(
                    		resultSet.getInt("id_silla"),
                    		resultSet.getInt("posicion"),
                    		resultSet.getInt("id_sala")
                    		);
                    
                    matrizSillas[fila][columna] = silla;
                    
                    columna++; // Pasar a la siguiente fila
                    if (columna == cantColumnas) {
                        columna = 0;
                        fila++;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("ERROR AL OBTENER MATRIZ DE SILLAS");
            ex.printStackTrace();
        }
        return matrizSillas;
	}
	
	private SillaDTO[][] generarMatriz(int cantidadSillas) {
		int columnas = 8;
		int filas = cantidadSillas / columnas;
        SillaDTO[][] matriz;

        if (cantidadSillas % columnas != 0) {
            matriz = new SillaDTO[filas + 1][];

            for (int i = 0; i < filas; i++) {
                matriz[i] = new SillaDTO[columnas];
            }
            int puestosFaltantes = cantidadSillas % columnas;
            matriz[matriz.length - 1] = new SillaDTO[puestosFaltantes];
        } else {
            matriz = new SillaDTO[filas][columnas];
        }
        return matriz;
	}
}
