package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;

public class ProveedorMapper {

    public static ProveedorDTO toDTO(Proveedor proveedor) {
        if (proveedor == null) return null;
        return new ProveedorDTO(
            proveedor.getNit(),
            proveedor.getNombre(),
            proveedor.getContacto(),
            proveedor.getEmail(),
            proveedor.getTelefono(),
            proveedor.isActivo()
        );
    }

    public static Proveedor toEntity(ProveedorDTO dto) {
        if (dto == null) return null;
        Proveedor p = new Proveedor(
                dto.getNit(),
                dto.getNombre(),
                dto.getContacto(),
                dto.getEmail(),
                dto.getTelefono()
        );
        if (dto.getEstado()) p.activar(); else p.inactivar();
        return p;
    }
    
}
