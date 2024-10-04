package DTOs;

import java.sql.Date;

public class ComentarioDTO {
    
    private int id_comentario;
    private String contenido;
    private UsuarioDTO usuario;
    private Date fecha;

    public ComentarioDTO(int id_comentario, String contenido, UsuarioDTO usuario, Date fecha) {
        this.id_comentario = id_comentario;
        this.contenido = contenido;
        this.usuario = usuario;
        this.fecha = fecha;
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
