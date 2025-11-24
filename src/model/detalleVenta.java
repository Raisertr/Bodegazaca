/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class detalleVenta {
     private int idDetalle;
    private int cantidad;
    private BigDecimal precio;
    private BigDecimal subtotal;
    private int idVenta;
    private int idProducto;
    private int idLote; // Para disminuir stock

    public detalleVenta() {}

    // Getters y Setters
    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getIdLote() { return idLote; }
    public void setIdLote(int idLote) { this.idLote = idLote; }

    // Método para calcular subtotal
    public void calcularSubtotal() {
        this.subtotal = this.precio.multiply(new BigDecimal(cantidad)).setScale(2, RoundingMode.HALF_UP);
    }
}
