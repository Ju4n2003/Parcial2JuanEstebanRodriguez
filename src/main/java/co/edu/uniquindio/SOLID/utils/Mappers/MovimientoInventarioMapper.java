package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.MovimientoInventario;
import co.edu.uniquindio.SOLID.Model.DTO.MovimientoInventarioDTO;

public class MovimientoInventarioMapper {

    public static MovimientoInventarioDTO toDTO(MovimientoInventario mov) {
        MovimientoInventarioDTO dto = new MovimientoInventarioDTO();
        dto.setId(mov.getId());
        dto.setTipo(mov.getTipo().name());
        dto.setFecha(mov.getFecha());
        dto.setSkuProducto(mov.getProducto() != null ? mov.getProducto().getSku() : null);
        dto.setCantidad(mov.getCantidad());
        dto.setReferencia(mov.getReferencia());
        return dto;
    }
}
