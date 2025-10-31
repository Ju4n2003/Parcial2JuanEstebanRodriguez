package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.*;
import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;

import java.util.List;

public class InventarioService {

    private final Minimercado minimercado;
    private final CatalogoProductosService catalogoProductosService;

    public InventarioService() {
        this.minimercado = Minimercado.getInstancia();
        this.catalogoProductosService = new CatalogoProductosService();
    }

    public List<Proveedor> listarProveedores() {
        return minimercado.getProveedores();
    }

    public List<Producto> listarProductos() {
        return minimercado.getProductos();
    }

    public List<MovimientoInventario> listarMovimientos() {
        return minimercado.getMovimientos();
    }

    public int consultarStock(String sku) {
        Producto p = catalogoProductosService.buscarProducto(sku);
        if (p == null) throw new IllegalArgumentException("Producto no encontrado: " + sku);
        return p.getStock();
    }

    public void registrarEntrada(EntradaInventarioDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Entrada inválida");
        if (dto.getNitProveedor() == null || dto.getNitProveedor().isBlank())
            throw new IllegalArgumentException("NIT de proveedor requerido");
        if (dto.getSkuProducto() == null || dto.getSkuProducto().isBlank())
            throw new IllegalArgumentException("SKU de producto requerido");
        if (dto.getCantidad() <= 0)
            throw new IllegalArgumentException("Cantidad debe ser > 0");

        Proveedor proveedor = minimercado.buscarProveedor(dto.getNitProveedor());
        if (proveedor == null) throw new IllegalArgumentException("Proveedor no encontrado: " + dto.getNitProveedor());

        Producto producto = catalogoProductosService.buscarProducto(dto.getSkuProducto());
        if (producto == null) throw new IllegalArgumentException("Producto no encontrado: " + dto.getSkuProducto());

        minimercado.registrarEntradaInventario(proveedor, producto, dto.getCantidad());
    }
}
