/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
public class vencimientoDTO {
    private String codigo;
    private String producto;
    private String numeroLote;
    private Date fechaVencimiento;
    private int stockActual; // El stock ESPECÍFICO de ese lote
    private String proveedor;
    private int diasRestantes; // Dato calculado por SQL
    
    public vencimientoDTO() {
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public String getNumeroLote() { return numeroLote; }
    public void setNumeroLote(String numeroLote) { this.numeroLote = numeroLote; }

    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public int getDiasRestantes() { return diasRestantes; }
    public void setDiasRestantes(int diasRestantes) { this.diasRestantes = diasRestantes; }
}
