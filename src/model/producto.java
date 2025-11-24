/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author juane
 */
public class producto {
    private int idProducto;
    private String sku;
    private String codigoBarras;
    private String nombre;
    private int idCategoria;
    private BigDecimal precio;
    private double costo;
    private int stock;
    private int idProveedor;
    private int idUsuario; // Usuario que registró el producto
    private String estado;
    private Date fechaRegistro;
    private String categoria;
    
    
     public producto() {}

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
    
     public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }


    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString() {
        return nombre;
    }
}
