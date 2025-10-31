package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;

public class EmpleadoMapper {
    public static EmpleadoDTO toDTO(Empleado empleado) {
        if (empleado == null) return null;
        String rol = empleado.getRol() != null ? empleado.getRol().name() : null;
        return new EmpleadoDTO(
            empleado.getId(),
            empleado.getNombre(),
            rol,
            empleado.isActivo()
        );
    }
}
