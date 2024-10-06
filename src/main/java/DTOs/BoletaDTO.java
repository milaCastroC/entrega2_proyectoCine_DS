package DTOs;

public class BoletaDTO {

   private int id_boleta;
   private int id_funcion;
   private int  id_silla;
   private int id_compra;
   private double precio;

    public BoletaDTO(int id_boleta, int id_funcion, int id_silla, int id_compra, double precio) {
        this.id_boleta = id_boleta;
        this.id_funcion = id_funcion;
        this.id_silla = id_silla;
        this.id_compra = id_compra;
        this.precio = precio;
    }

    public int getId_boleta() {
        return id_boleta;
    }

    public int getId_funcion() {
        return id_funcion;
    }

    public void setIdf_uncion(int idf_uncion) {
        this.id_funcion = idf_uncion;
    }

    public int getId_silla() {
        return id_silla;
    }

    public void setId_silla(int id_silla) {
        this.id_silla = id_silla;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
