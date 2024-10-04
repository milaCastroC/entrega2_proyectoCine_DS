package DTOs;

import java.sql.Date;

public class CompraDTO {
    
    private int idCompra;
    private Date fecha;
    private int idUsuario;
    private int cantidad;

    public CompraDTO(int idCompra, Date fecha, int idUsuario, int cantidad) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.cantidad = cantidad;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public int getIdUsuario() {
    	return idUsuario; 
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
