package DTOs;

import java.sql.Date;
import java.sql.Time;


public class FuncionDTO {

    private int id_funcion;
    private String codigo_funcion;
    private int id_sala;
    private Date fecha;
    private Time hora;
    private int id_pelicula;

    public FuncionDTO(int id_funcion, String codigo_funcion, int id_sala, Date fecha, Time hora, int id_pelicula) {
        this.id_funcion = id_funcion;
        this.codigo_funcion = codigo_funcion;
        this.id_sala = id_sala;
        this.fecha = fecha;
        this.hora = hora;
        this.id_pelicula = id_pelicula;
    }

    public int getId_funcion() {
        return id_funcion;
    }

    public String getCodigo_funcion() {
        return codigo_funcion;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    @Override
    public String toString() {
        return fecha + " - " + hora;
    }
    
    
}
