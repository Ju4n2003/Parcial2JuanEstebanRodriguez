package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.Service.EmpleadoService;
import co.edu.uniquindio.SOLID.utils.Mappers.EmpleadoMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EmpresaAdminFacade {

    private final EmpleadoService empleadoService;

    public EmpresaAdminFacade() {
        this.empleadoService = new EmpleadoService();
    }

    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoService.obtenerEmpleados().stream()
                .map(EmpleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmpleadoDTO crearEmpleado(EmpleadoDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Empleado inválido");
        Empleado creado = empleadoService.crearEmpleado(dto.getId(), dto.getNombre(), dto.getRol());
        return EmpleadoMapper.toDTO(creado);
    }

    public EmpleadoDTO actualizarEmpleado(EmpleadoDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Empleado inválido");
        Empleado actualizado = empleadoService.actualizarEmpleado(dto.getId(), dto.getNombre(), dto.getRol(), dto.isEstado());
        return EmpleadoMapper.toDTO(actualizado);
    }

    public void eliminarEmpleado(String id) {
        empleadoService.eliminarEmpleado(id);
    }

    public EmpleadoDTO activarEmpleado(String id) {
        Empleado actualizado = empleadoService.actualizarEmpleado(id, null, null, true);
        return EmpleadoMapper.toDTO(actualizado);
    }

    public EmpleadoDTO inactivarEmpleado(String id) {
        Empleado actualizado = empleadoService.actualizarEmpleado(id, null, null, false);
        return EmpleadoMapper.toDTO(actualizado);
    }
}
