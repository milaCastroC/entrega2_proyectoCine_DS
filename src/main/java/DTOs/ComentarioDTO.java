package DTOs;

import java.sql.Date;

public class ComentarioDTO {
    
    private int id_comentario;
    private String contenido;
    private int idUsuario;
    private Date fecha;
    private int idPelicula;

    public ComentarioDTO(int id_comentario, String contenido, int idUsuario, Date fecha, int idPelicula) {
        this.id_comentario = id_comentario;
        this.contenido = contenido;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.idPelicula = idPelicula;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public int getIdPelicula() {
    	return idPelicula;
    }
}
