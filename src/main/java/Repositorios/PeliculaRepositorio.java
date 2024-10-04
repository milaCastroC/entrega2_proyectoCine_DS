package Repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DTOs.PeliculaDTO;
import DatabaseConfig.DatabaseConfig;

public class PeliculaRepositorio {

	public PeliculaDTO buscarPorId(int id) throws SQLException {
		String query = "SELECT * FROM pelicula WHERE id_pelicula = ?";
		try (Connection connection = DatabaseConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
				
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return new PeliculaDTO(
						resultSet.getInt("id_pelicula"), 
						resultSet.getString("codigo_pelicula"),
						resultSet.getString("nombre"),
						resultSet.getDate("fecha_estreno"), 
						resultSet.getString("director"),
						resultSet.getInt("duracion_minutos"), 
						resultSet.getString("genero"),
						resultSet.getString("sinopsis"));
			}
		} catch (SQLException e) {
			 System.err.println("Error al buscar película por id.");
			e.printStackTrace();
		}
		return null;
	}
	
	public PeliculaDTO buscarPorCodigo(String codigo) throws SQLException {
	    String query = "SELECT * FROM pelicula WHERE codigo_pelicula = ?";
	    try (Connection connection = DatabaseConfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, codigo);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                return new PeliculaDTO(
	                        resultSet.getInt("id_pelicula"),
	                        resultSet.getString("codigo_pelicula"),
	                        resultSet.getString("nombre"),
	                        resultSet.getDate("fecha_estreno"),
	                        resultSet.getString("director"),
	                        resultSet.getInt("duracion_minutos"),
	                        resultSet.getString("genero"),
	                        resultSet.getString("sinopsis")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al buscar película por código.");
	        e.printStackTrace();
	    }
	    return null;
	}


	public void agregarPelicula(PeliculaDTO pelicula) throws SQLException {
		String codigo_pelicula = pelicula.getCodigo_pelicula();
		String nombre = pelicula.getNombre();
		Date fecha_estreno = pelicula.getFecha_estreno();
		String director = pelicula.getDirector();
		int duracion_minutos = pelicula.getDuracion_minutos();
		String genero = pelicula.getGenero();
		String sinopsis = pelicula.getSipnosis();

		// Formatear la fecha en formato (yyyy-MM-dd)
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fechaEstrenoStr = dateFormat.format(fecha_estreno);

		try (Connection connection = DatabaseConfig.getConnection()) {
			Statement statement = connection.createStatement();
			String insertSQL = String.format("INSERT INTO pelicula (nombre,codigo_pelicula, fecha_estreno, director, duracion_minutos, genero, sinopsis) VALUES ('%s', '%s', '%s', '%s', %d, '%s', '%s')",nombre,codigo_pelicula, fechaEstrenoStr, director, duracion_minutos, genero, sinopsis);
			
			statement.executeUpdate(insertSQL);
			System.out.println("Pelicula insertada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

	public List<PeliculaDTO> obetenerPeliculas() throws SQLException {
		List<PeliculaDTO> peliculas = new ArrayList<>();
		String query = "SELECT * FROM pelicula";

		try (Connection connection = DatabaseConfig.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				PeliculaDTO pelicula = new PeliculaDTO(
						resultSet.getInt("id_pelicula"), 
						resultSet.getString("codigo_pelicula"),
						resultSet.getString("nombre"),
						resultSet.getDate("fecha_estreno"), 
						resultSet.getString("director"),
						resultSet.getInt("duracion_minutos"), 
						resultSet.getString("genero"),
						resultSet.getString("sinopsis"));
				peliculas.add(pelicula);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return peliculas;
	}

	public void eliminarPelicula(String codigo) throws SQLException {
		String deleteSQL = "DELETE FROM pelicula WHERE codigo_pelicula = '" + codigo + "'";

		try (Connection connection = DatabaseConfig.getConnection();
				Statement statement = connection.createStatement()) {

			statement.executeUpdate(deleteSQL);
			System.out.println("Pelicula eliminada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actualizarPelicula(String codigo, PeliculaDTO nuevaPelicula) throws SQLException {
		String nombre = nuevaPelicula.getNombre();
		Date fecha_estreno = nuevaPelicula.getFecha_estreno();
		String director = nuevaPelicula.getDirector();
		int duracion_minutos = nuevaPelicula.getDuracion_minutos();
		String genero = nuevaPelicula.getGenero();
		String sinopsis = nuevaPelicula.getSipnosis();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fechaEstrenoStr = dateFormat.format(fecha_estreno);

		String updateSQL = String.format( "UPDATE pelicula SET nombre = '%s', fecha_estreno = '%s', director = '%s', duracion_minutos = %d, genero = '%s', sinopsis = '%s' WHERE codigo_pelicula = '%s'", nombre, fechaEstrenoStr, director, duracion_minutos, genero, sinopsis, codigo);

		try (Connection connection = DatabaseConfig.getConnection();
				Statement statement = connection.createStatement()) {

			statement.executeUpdate(updateSQL);
			System.out.println("Pelicula actualizada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
