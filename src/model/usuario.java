/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juane
 */
public class usuario {
     private int idUsuario;
    private String nombre;
    private String usuario;
    private String rol;
    private String estado;
    private String contraseña;

    // Constructor vacío
    public usuario() {}

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    
     public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    
    // ❗ No usar fuera del DAO
    public  String getContraseña() {
    return contraseña;
}
}
