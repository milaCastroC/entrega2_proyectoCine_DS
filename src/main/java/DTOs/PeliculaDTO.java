package DTOs;

import java.sql.Date;

public class PeliculaDTO {

    private int id_pelicula;
    private String codigo_pelicula;
    private String nombre;
    private Date fecha_estreno;
    private String director;
    private int duracion_minutos;
    private String genero;
    private String sipnosis;

    public PeliculaDTO(int id_pelicula, String codigo_pelicula, String nombre, Date fecha_estreno, String director, int duracion_minutos, String genero, String sipnosis) {
        this.id_pelicula = id_pelicula;
        this.codigo_pelicula = codigo_pelicula;
        this.nombre = nombre;
        this.fecha_estreno = fecha_estreno;
        this.director = director;
        this.duracion_minutos = duracion_minutos;
        this.genero = genero;
        this.sipnosis = sipnosis;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }
    
    public String getCodigo_pelicula() {
    	return codigo_pelicula;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_estreno() {
        return fecha_estreno;
    }

    public void setFecha_estreno(Date fecha_estreno) {
        this.fecha_estreno = fecha_estreno;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuracion_minutos() {
        return duracion_minutos;
    }

    public void setDuracion_minutos(int duracion_minutos) {
        this.duracion_minutos = duracion_minutos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSipnosis() {
        return sipnosis;
    }

    public void setSipnosis(String sipnosis) {
        this.sipnosis = sipnosis;
    }

    @Override
    public String toString() {
        return codigo_pelicula + " - " + nombre;
    }
    
    
}
