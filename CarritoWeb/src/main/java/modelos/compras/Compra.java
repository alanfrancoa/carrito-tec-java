package modelos.compras;

import java.util.Date;
import java.util.List;
import modelos.carrito.Renglon;

public class Compra {
    private String nombreCliente;          // Cliente que realizó la compra
    private List<Renglon> detalleCompra;   // Lista de artículos comprados
    private String numeroFactura;          // Número único de factura
    private double montoTotal;             // Total de la compra
    private Date fechaCompra;              // Fecha y hora de la compra

    public Compra(String nombreCliente, List<Renglon> detalleCompra, String numeroFactura, double montoTotal) {
        this.nombreCliente = nombreCliente;
        this.detalleCompra = detalleCompra;
        this.numeroFactura = numeroFactura;
        this.montoTotal = montoTotal;
        this.fechaCompra = new Date(); // Fecha actual en el momento de la compra
    }

    // Getters
    public String getNombreCliente() {
        return nombreCliente;
    }

    public List<Renglon> getDetalleCompra() {
        return detalleCompra;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }
}