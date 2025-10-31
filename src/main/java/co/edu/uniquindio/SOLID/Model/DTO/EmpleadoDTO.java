package co.edu.uniquindio.SOLID.Model.DTO;

public class EmpleadoDTO {
    private String id;
    private String nombre;
    private String rol;
    private boolean estado;
    
    public EmpleadoDTO(){
        
    }

    public EmpleadoDTO(String id, String nombre, String rol, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.estado = true;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO [id=" + id + ", nombre=" + nombre + ", rol=" + rol + ", estado=" + estado + "]";
    }
    
}
