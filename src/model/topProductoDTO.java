/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juane
 */
public class topProductoDTO {
    private String codigo;
    private String producto;
    private String categoria;
    private int cantidadTotal; // La suma de todas las ventas

    public topProductoDTO() {
    }

    public topProductoDTO(String codigo, String producto, String categoria, int cantidadTotal) {
        this.codigo = codigo;
        this.producto = producto;
        this.categoria = categoria;
        this.cantidadTotal = cantidadTotal;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(int cantidadTotal) { this.cantidadTotal = cantidadTotal; }
}
