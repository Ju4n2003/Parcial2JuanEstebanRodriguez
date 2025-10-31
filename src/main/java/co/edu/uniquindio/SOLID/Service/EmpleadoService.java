package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.Model.Minimercado;

import java.util.List;

public class EmpleadoService {
    private final Minimercado minimercado;

    public EmpleadoService() {
        this.minimercado = Minimercado.getInstancia();
    }

    public List<Empleado> obtenerEmpleados() {
        return minimercado.getEmpleados();
    }

    public Empleado buscarEmpleado(String id) {
        return minimercado.buscarEmpleado(id);
    }

    public Empleado crearEmpleado(String id, String nombre, String rol) {
        return minimercado.crearEmpleado(id, nombre, rol);
    }

    public Empleado actualizarEmpleado(String id, String nombre, String rol, Boolean activo) {
        return minimercado.actualizarEmpleado(id, nombre, rol, activo);
    }

    public void eliminarEmpleado(String id) {
        minimercado.eliminarEmpleado(id);
    }
}
