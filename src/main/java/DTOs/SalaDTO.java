package DTOs;

public class SalaDTO {
    
    private int id_sala;
    private String codigoSala;
    private String nombreSala;
    private SillaDTO[] sillas;
    private int capacidad;

    public SalaDTO(int id_sala, String codigoSala, String nombreSala, int capacidad) {
        this.id_sala = id_sala;
    	this.codigoSala = codigoSala;
    	this.nombreSala = nombreSala;
        this.capacidad = capacidad;
    }

    public int getId_sala() {
        return id_sala;
    }
    
    public String getCodigoSala() {
    	return codigoSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public SillaDTO[] getSillas() {
        return sillas;
    }

    public void setSillas(SillaDTO[] sillas) {
        this.sillas = sillas;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return codigoSala + " - " + nombreSala;
    }
}
