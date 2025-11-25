/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class venta {
    private int idVenta;
    private String numeroDocumento;
    private Date fecha;
    private BigDecimal subTotal;
    private BigDecimal igv;
    private BigDecimal total;
    private int idUsuario;
     private String estado = "Activo"; // Valor por defecto

    private List<detalleVenta> detalles; // Relación con DetalleVenta

    public venta() {}

    // Getters y Setters
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public BigDecimal getSubTotal() { return subTotal; }
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }

    public BigDecimal getIgv() { return igv; }
    public void setIgv(BigDecimal igv) { this.igv = igv; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

   

public String getEstado() { return estado; }
public void setEstado(String estado) { this.estado = estado; }

    public List<detalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<detalleVenta> detalles) { this.detalles = detalles; }

    // Método para calcular totales
   public void calcularTotales() {
    if (detalles != null && !detalles.isEmpty()) {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (detalleVenta d : detalles) {
            subtotal = subtotal.add(d.getSubtotal());
        }
        this.subTotal = subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.igv = subtotal.multiply(new BigDecimal("0.18")).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.total = subtotal.add(igv).setScale(2, BigDecimal.ROUND_HALF_UP);
    } else {
        this.subTotal = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.igv = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.total = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    this.estado = "Activo"; // Asegura que el estado esté asignad
}
}
