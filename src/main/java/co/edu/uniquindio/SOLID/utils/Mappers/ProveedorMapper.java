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
    
}
