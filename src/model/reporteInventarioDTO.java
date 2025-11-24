/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juane
 */
public class reporteInventarioDTO {
    private String codigo;
    private String producto;
    private String categoria;
    private int stock;
    private double precio;
    private String proveedor;

    public reporteInventarioDTO() {
    }

    public reporteInventarioDTO(String codigo, String producto, String categoria, int stock, double precio, String proveedor) {
        this.codigo = codigo;
        this.producto = producto;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
        this.proveedor = proveedor;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }
}
