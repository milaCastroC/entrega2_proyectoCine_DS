package DTOs;

public class UsuarioDTO {

    private int id_usuario;
    private String correo;
    private String contrasena;
    private String rol;
    private String nombre;
    private String telefono;
    
    public static final String ADMINISTRADOR = "Administrador";
    public static final String CLIENTE = "Cliente";

    public UsuarioDTO(int id_usuario, String correo, String contrasena, String rol, String nombre, String telefono) {
        this.id_usuario = id_usuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    public UsuarioDTO(String correo, String contrasena, String rol, String nombre, String telefono) {
        this.id_usuario = id_usuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
