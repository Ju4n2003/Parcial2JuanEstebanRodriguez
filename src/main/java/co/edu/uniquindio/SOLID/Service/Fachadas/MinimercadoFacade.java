package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.ClienteDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProductoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.PedidoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ItemPedidoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ResumenPedidoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.Proveedor;

import co.edu.uniquindio.SOLID.Service.ClienteService;
import co.edu.uniquindio.SOLID.Service.ProductoService;
import co.edu.uniquindio.SOLID.Service.PedidoService;
import co.edu.uniquindio.SOLID.Service.CatalogoProductosService;

import java.util.List;
import java.time.LocalDateTime;

/**
 * Facade que actúa como punto de entrada
 * Delega toda la lógica a los servicios correspondientes
 */
public class MinimercadoFacade {
    
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;
    
    public MinimercadoFacade() {
        this.clienteService = new ClienteService();
        this.productoService = new ProductoService();
        CatalogoProductosService catalogoProductosService = new CatalogoProductosService();
        this.pedidoService = new PedidoService(catalogoProductosService);
    }
    
    // ========== DELEGACIÓN SIMPLE A SERVICIOS ==========
    
    // Clientes
    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }
    
    public ClienteDTO buscarClientePorCedula(String cedula) {
        return clienteService.buscarClientePorCedula(cedula);
    }
    
    public boolean agregarCliente(ClienteDTO clienteDTO) {
        return clienteService.agregarCliente(clienteDTO);
    }
    
    public boolean actualizarCliente(ClienteDTO clienteDTO) {
        return clienteService.actualizarCliente(clienteDTO);
    }
    
    public boolean eliminarCliente(String cedula) {
        return clienteService.eliminarCliente(cedula);
    }
    
    public boolean existeCliente(String cedula) {
        return clienteService.existeCliente(cedula);
    }
    
    // Productos
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }
    
    public ProductoDTO buscarProductoPorSku(String sku) {
        return productoService.buscarProductoPorSku(sku);
    }
    
    public boolean agregarProducto(ProductoDTO productoDTO) {
        return productoService.agregarProducto(productoDTO);
    }
    
    public boolean actualizarProducto(ProductoDTO productoDTO) {
        return productoService.actualizarProducto(productoDTO);
    }
    
    public boolean eliminarProducto(String sku) {
        return productoService.eliminarProducto(sku);
    }
    
    public boolean existeProducto(String sku) {
        return productoService.existeProducto(sku);
    }
    
    // Pedidos
    public ResumenPedidoDTO procesarPedido(PedidoDTO pedidoDTO) {
        return pedidoService.procesarPedido(pedidoDTO);
    }
    
    public double calcularSubtotal(List<ItemPedidoDTO> items) {
        return pedidoService.calcularSubtotal(items);
    }
    
    public double calcularCostoEnvio(String tipoEnvio) {
        return pedidoService.calcularCostoEnvio(tipoEnvio);
    }
    
    public double calcularTotal(double subtotal, double costoEnvio) {
        return pedidoService.calcularTotal(subtotal, costoEnvio);
    }

    // ========== LÓGICA SIMPLE (PARTE 2) ==========
    /**
     * Procesa un pedido con una lógica sencilla reutilizando cálculos existentes.
     */
    public ResumenPedidoDTO procesarPedidoSimple(PedidoDTO pedidoDTO) {
        if (pedidoDTO == null) throw new IllegalArgumentException("Pedido no válido");
        if (pedidoDTO.itemsPedido == null || pedidoDTO.itemsPedido.isEmpty()) {
            throw new IllegalArgumentException("El pedido no tiene items");
        }

        double subtotal = calcularSubtotal(pedidoDTO.itemsPedido);
        double costoEnvio = calcularCostoEnvio("ESTANDAR");
        double total = calcularTotal(subtotal, costoEnvio);

        ClienteDTO cliente = buscarClientePorCedula(pedidoDTO.idCliente);

        ResumenPedidoDTO resumen = new ResumenPedidoDTO();
        resumen.codigo = pedidoDTO.codigo;
        resumen.fechaCreacion = LocalDateTime.now();
        resumen.nombreCliente = cliente != null ? cliente.getNombre() : pedidoDTO.idCliente;
        resumen.correoCliente = cliente != null ? cliente.getCorreo() : null;
        resumen.items = pedidoDTO.itemsPedido;
        resumen.direccionEnvio = pedidoDTO.direccionEnvio;
        resumen.notas = pedidoDTO.notas;
        resumen.codigoDescuento = pedidoDTO.codigoDescuento;
        resumen.subtotal = subtotal;
        resumen.costoEnvio = costoEnvio;
        resumen.total = total;
        resumen.metodoPago = "SIMPLE";
        resumen.tipoEnvio = "ESTANDAR";
        resumen.tipoNotificacion = "EMAIL";
        resumen.estado = "PROCESADO";

        return resumen;
    }

    /**
     * Registra una entrada de inventario simple utilizando el modelo directamente.
     */
    public void registrarEntradaSimple(EntradaInventarioDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Entrada inválida");
        if (dto.getCantidad() <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");

        Minimercado m = Minimercado.getInstancia();

        Proveedor proveedor = m.buscarProveedor(dto.getNitProveedor());
        if (proveedor == null) throw new IllegalArgumentException("Proveedor no encontrado: " + dto.getNitProveedor());

        Producto producto = null;
        for (Producto p : m.getProductos()) {
            if (p.getSku().equals(dto.getSkuProducto())) { producto = p; break; }
        }
        if (producto == null) throw new IllegalArgumentException("Producto no encontrado: " + dto.getSkuProducto());

        m.registrarEntradaInventario(proveedor, producto, dto.getCantidad());
    }
}