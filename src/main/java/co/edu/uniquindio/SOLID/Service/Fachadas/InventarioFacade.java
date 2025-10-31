package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.MovimientoInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProductoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.Service.InventarioService;
import co.edu.uniquindio.SOLID.Service.ProductoService;
import co.edu.uniquindio.SOLID.Service.ProveedorService;
import co.edu.uniquindio.SOLID.utils.Mappers.MovimientoInventarioMapper;
import co.edu.uniquindio.SOLID.utils.Mappers.ProveedorMapper;

import java.util.List;
import java.util.stream.Collectors;

public class InventarioFacade {

    private final InventarioService inventarioService;
    private final ProveedorService proveedorService;
    private final ProductoService productoService;

    public InventarioFacade() {
        this.inventarioService = new InventarioService();
        this.proveedorService = new ProveedorService();
        this.productoService = new ProductoService();
    }

    // Proveedores
    public List<ProveedorDTO> listarProveedores() {
        return proveedorService.listar().stream().map(ProveedorMapper::toDTO).collect(Collectors.toList());
    }

    public boolean crearProveedor(ProveedorDTO dto) {
        Proveedor p = ProveedorMapper.toEntity(dto);
        return proveedorService.crear(p);
    }

    public boolean actualizarProveedor(ProveedorDTO dto) {
        Proveedor p = ProveedorMapper.toEntity(dto);
        return proveedorService.actualizar(p);
    }

    public boolean eliminarProveedor(String nit) {
        return proveedorService.eliminar(nit);
    }

    public boolean activarProveedor(String nit) {
        return proveedorService.activar(nit);
    }

    public boolean inactivarProveedor(String nit) {
        return proveedorService.inactivar(nit);
    }

    // Productos (solo lectura)
    public List<ProductoDTO> listarProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    public int consultarStock(String sku) {
        return inventarioService.consultarStock(sku);
    }

    // Entradas y movimientos
    public void registrarEntrada(EntradaInventarioDTO dto) {
        inventarioService.registrarEntrada(dto);
    }

    public List<MovimientoInventarioDTO> listarMovimientos() {
        return inventarioService.listarMovimientos().stream()
                .map(MovimientoInventarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
