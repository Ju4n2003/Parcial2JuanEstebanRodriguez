package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Model.Proveedor;

import java.util.List;

public class ProveedorService {
    private final Minimercado minimercado;

    public ProveedorService() {
        this.minimercado = Minimercado.getInstancia();
    }

    public List<Proveedor> listar() {
        return minimercado.getProveedores();
    }

    public Proveedor buscar(String nit) {
        return minimercado.buscarProveedor(nit);
    }

    public boolean crear(Proveedor proveedor) {
        if (proveedor == null) return false;
        if (proveedor.getNit() == null || proveedor.getNit().isBlank()) return false;
        if (buscar(proveedor.getNit()) != null) throw new IllegalArgumentException("Ya existe proveedor con NIT: " + proveedor.getNit());
        if (proveedor.getEmail() != null && !proveedor.getEmail().isBlank() && !proveedor.getEmail().contains("@"))
            throw new IllegalArgumentException("Email inválido");
        minimercado.agregarProveedor(proveedor);
        return true;
    }

    public boolean actualizar(Proveedor proveedor) {
        if (proveedor == null || proveedor.getNit() == null || proveedor.getNit().isBlank()) return false;
        minimercado.actualizarProveedor(
                proveedor.getNit(),
                proveedor.getNombre(),
                proveedor.getContacto(),
                proveedor.getEmail(),
                proveedor.getTelefono(),
                proveedor.isActivo()
        );
        return true;
    }

    public boolean eliminar(String nit) {
        minimercado.eliminarProveedor(nit);
        return true;
    }

    public boolean activar(String nit) {
        minimercado.actualizarProveedor(nit, null, null, null, null, true);
        return true;
    }

    public boolean inactivar(String nit) {
        minimercado.actualizarProveedor(nit, null, null, null, null, false);
        return true;
    }
}
