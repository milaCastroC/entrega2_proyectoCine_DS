package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.FuncionDTO;
import DatabaseConfig.DatabaseConfig;
import Exceptions.PeliculaNoEncontradaException;


public class FuncionRepositorio {

    // Buscar función por ID
    public FuncionDTO buscarPorId(int id) throws SQLException {
        String query = "SELECT * FROM funcion WHERE id_funcion = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, id);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    
                    return new FuncionDTO(
                            resultSet.getInt("id_funcion"),
                            resultSet.getString("codigo_funcion"),
                            resultSet.getInt("id_sala"),
                            resultSet.getDate("fecha"),
                            resultSet.getTime("hora"),
                            resultSet.getInt("id_pelicula")
                    );
                } else {
                    return null; 
                }
            }
        } catch (SQLException ex) {
            System.err.println("NO SE ENCONTRÓ LA FUNCIÓN (ID)");
            ex.printStackTrace();
        }
        return null;
    }

    // Buscar función por código
    public FuncionDTO buscarPorCodigo(String codigo) throws SQLException {
        String query = "SELECT * FROM funcion WHERE codigo_funcion = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setString(1, codigo);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    
                    return new FuncionDTO(
                            resultSet.getInt("id_funcion"),
                            resultSet.getString("codigo_funcion"),
                            resultSet.getInt("id_sala"),
                            resultSet.getDate("fecha"),
                            resultSet.getTime("hora"),
                            resultSet.getInt("id_pelicula")
                    );
                } else {
                    return null; 
                }
            }
        } catch (SQLException ex) {
            System.err.println("NO SE ENCONTRÓ LA FUNCIÓN (CÓDIGO)");
            ex.printStackTrace();
        }
        return null;
    }

    // Agregar función
    public void agregarFuncion(FuncionDTO funcion) throws SQLException {
        String insertSQL = "INSERT INTO funcion (codigo_funcion, id_sala, fecha, hora, id_pelicula) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
             
            preparedStatement.setString(1, funcion.getCodigo_funcion());
            preparedStatement.setInt(2, funcion.getId_sala()); 
            preparedStatement.setDate(3, funcion.getFecha());
            preparedStatement.setTime(4, funcion.getHora());
            preparedStatement.setInt(5, funcion.getId_pelicula()); 
            
            preparedStatement.executeUpdate();
            System.out.println("Función insertada correctamente.");
        } catch (SQLException ex) {
            System.err.println("ERROR AL INSERTAR LA FUNCION");
            ex.printStackTrace();
        }
    }

    // Eliminar función
    public void eliminarFuncion(String codigo) throws SQLException {
        String deleteSQL = "DELETE FROM funcion WHERE codigo_funcion = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
             
            preparedStatement.setString(1, codigo);
            preparedStatement.executeUpdate();
            System.out.println("Función eliminada correctamente.");
        } catch (SQLException ex) {
            System.err.println("ERROR AL ELIMINAR LA FUNCION");
            ex.printStackTrace();
        }
    }

    // Obtener funciones por película
    public List<FuncionDTO> obtenerFuncionesPorPelicula(int idPelicula) throws SQLException, PeliculaNoEncontradaException {
        List<FuncionDTO> funciones = new ArrayList<>();
        String query = "SELECT * FROM funcion WHERE id_pelicula = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, idPelicula);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    
                    funciones.add(new FuncionDTO(
                            resultSet.getInt("id_funcion"),
                            resultSet.getString("codigo_funcion"),
                            resultSet.getInt("id_sala"),
                            resultSet.getDate("fecha"),
                            resultSet.getTime("hora"),
                            resultSet.getInt("id_pelicula")
                    ));
                }
            }
        } catch (SQLException ex) {
            System.err.println("ERROR AL OBTENER FUNCIONES POR PELICULA");
            ex.printStackTrace();
        }
        return funciones;
    }
    
    // Obtener funciones por película
    public List<FuncionDTO> obtenerFuncionesPorSala(int idSala) throws SQLException, PeliculaNoEncontradaException {
        List<FuncionDTO> funciones = new ArrayList<>();
        String query = "SELECT * FROM funcion WHERE id_sala = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, idSala);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    
                    funciones.add(new FuncionDTO(
                            resultSet.getInt("id_funcion"),
                            resultSet.getString("codigo_funcion"),
                            resultSet.getInt("id_sala"),
                            resultSet.getDate("fecha"),
                            resultSet.getTime("hora"),
                            resultSet.getInt("id_pelicula")
                    ));
                }
            }
        } catch (SQLException ex) {
            System.err.println("ERROR AL OBTENER FUNCIONES POR PELICULA");
            ex.printStackTrace();
        }
        return funciones;
    }
    
 // Obtener todas las funciones
    public List<FuncionDTO> obtenerTodasLasFunciones() throws SQLException, PeliculaNoEncontradaException {
        List<FuncionDTO> funciones = new ArrayList<>();
        String query = "SELECT * FROM funcion";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                
                funciones.add(new FuncionDTO(
                        resultSet.getInt("id_funcion"),
                        resultSet.getString("codigo_funcion"),
                        resultSet.getInt("id_sala"),
                        resultSet.getDate("fecha"),
                        resultSet.getTime("hora"),
                        resultSet.getInt("id_pelicula")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("ERROR AL OBTENER TODAS LAS FUNCIONES");
            ex.printStackTrace();
        }
        return funciones;
    }
    
    public List<Integer> getPeliculasConFunciones(){
    	List<Integer> idsPeliculas = new ArrayList<>();
        String query = "SELECT DISTINCT id_pelicula FROM funcion; "; // DISTINCT hace que solo se muestre una vez valores repetidos
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                idsPeliculas.add(resultSet.getInt("id_pelicula"));
            }
        } catch (SQLException ex) {
            System.err.println("ERROR AL OBTENER LOS IDS DE LAS PELICULAS");
            ex.printStackTrace();
        }
        return idsPeliculas;
    }
}
