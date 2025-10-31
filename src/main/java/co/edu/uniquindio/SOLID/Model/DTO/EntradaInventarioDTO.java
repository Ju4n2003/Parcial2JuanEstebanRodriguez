package co.edu.uniquindio.SOLID.Model.DTO;

import java.time.LocalDateTime;

public class EntradaInventarioDTO {
    private String nitProveedor;
    private String skuProducto;
    private int cantidad;
    private LocalDateTime fecha;

    public EntradaInventarioDTO() {}

    public EntradaInventarioDTO(String nitProveedor, String skuProducto, int cantidad, LocalDateTime fecha) {
        this.nitProveedor = nitProveedor;
        this.skuProducto = skuProducto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getNitProveedor() { return nitProveedor; }
    public void setNitProveedor(String nitProveedor) { this.nitProveedor = nitProveedor; }

    public String getSkuProducto() { return skuProducto; }
    public void setSkuProducto(String skuProducto) { this.skuProducto = skuProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
