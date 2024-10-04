package DTOs;

public class SillaDTO {

    private int id_silla;
    private String estado;
    private int identificador;
    private int id_sala;
    
    private SalaDTO sala;
    
    public static final String OCUPADO = "Ocupado";
    public static final String DISPONIBLE = "Disponible";

    public SillaDTO(int id_silla, int identificador, int id_sala) {
        this.id_silla = id_silla;
        this.identificador = identificador;
        this.id_sala = id_sala;
    }
    
    
    public int getId_silla() {
        return id_silla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
    public int getIdSala() {
    	return id_sala;
    }
    

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }
    
}
