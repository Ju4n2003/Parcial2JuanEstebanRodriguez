package co.edu.uniquindio.SOLID.Model.DTO;

public class ProveedorDTO {
    private String nit;
    private String nombre;
    private String contacto;
    private String email;
    private String telefono;
    private boolean estado;

    public ProveedorDTO() {
    }

    public ProveedorDTO(String nit, String nombre, String contacto, String email, String telefono, boolean estado) {
        this.nit = nit;
        this.nombre = nombre;
        this.contacto = contacto;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre + " (" + nit + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProveedorDTO that = (ProveedorDTO) o;
        return nit != null && nit.equals(that.nit);
    }

    @Override
    public int hashCode() {
        return nit != null ? nit.hashCode() : 0;
    }
}
