package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.EntradaInventario;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;

import java.util.List;

public class EntradaInventarioMapper {

    public static EntradaInventario toEntity(EntradaInventarioDTO dto, Proveedor proveedor, List<Producto> productos) {
        EntradaInventario entrada = new EntradaInventario("ENT-" + System.currentTimeMillis(), proveedor);
        if (dto.getSkuProducto() != null && dto.getCantidad() > 0) {
            Producto p = productos.stream()
                    .filter(prod -> prod.getSku().equals(dto.getSkuProducto()))
                    .findFirst()
                    .orElse(null);
            if (p != null) entrada.agregarItem(p, dto.getCantidad());
        }
        return entrada;
    }
}
