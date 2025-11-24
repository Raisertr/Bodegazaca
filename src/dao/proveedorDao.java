/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.proveedor;
import util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class proveedorDao {
        /**
     * Guarda un nuevo proveedor en la base de datos.
     */
    public List<String>listarNombres() {
        String sql = "SELECT razonSocial FROM proveedor";
        List<String> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getString("razonSocial"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public String obtenerRazonSocialPorId(int idProveedor) {
    String sql = "SELECT razonSocial FROM PROVEEDOR WHERE idProveedor = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idProveedor);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("razonSocial");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
    public int obtenerIdPorNombre(String nombre) {
    String sql = "SELECT idProveedor FROM proveedor WHERE razonSocial = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nombre);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("idProveedor");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Si no encuentra
}
    public boolean guardar(proveedor proveedor) {
        String sql = "INSERT INTO PROVEEDOR (razonSocial, telefono, correo, direccion, ruc, contacto, correoContacto, estado, idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getRazonSocial());
            stmt.setString(2, proveedor.getTelefono());
            stmt.setString(3, proveedor.getCorreo());
            stmt.setString(4, proveedor.getDireccion());
            stmt.setString(5, proveedor.getRuc());
            stmt.setString(6, proveedor.getContacto());
            stmt.setString(7, proveedor.getCorreoContacto());
            stmt.setString(8, proveedor.getEstado());
            stmt.setInt(9, proveedor.getIdUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza un proveedor existente.
     */
    public boolean actualizar(proveedor proveedor) {
        String sql = "UPDATE PROVEEDOR SET razonSocial = ?, telefono = ?, correo = ?, direccion = ?, ruc = ?, contacto = ?, correoContacto = ?, estado = ?, idUsuario = ? WHERE idProveedor = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getRazonSocial());
            stmt.setString(2, proveedor.getTelefono());
            stmt.setString(3, proveedor.getCorreo());
            stmt.setString(4, proveedor.getDireccion());
            stmt.setString(5, proveedor.getRuc());
            stmt.setString(6, proveedor.getContacto());
            stmt.setString(7, proveedor.getCorreoContacto());
            stmt.setString(8, proveedor.getEstado());
            stmt.setInt(9, proveedor.getIdUsuario());
            stmt.setInt(10, proveedor.getIdProveedor());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Desactiva un proveedor por su ID.
     */
    public boolean desactivar(int idProveedor) {
        String sql = "UPDATE PROVEEDOR SET estado = 'I' WHERE idProveedor = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProveedor);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene todos los proveedores activos.
     */
    public List<proveedor> listarActivos() {
        String sql = "SELECT idProveedor, razonSocial, telefono, correo, direccion, ruc, contacto, correoContacto, estado, idUsuario FROM PROVEEDOR WHERE estado = 'A'";
        List<proveedor> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                proveedor p = new proveedor();
                p.setIdProveedor(rs.getInt("idProveedor"));
                p.setRazonSocial(rs.getString("razonSocial"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo"));
                p.setDireccion(rs.getString("direccion"));
                p.setRuc(rs.getString("ruc"));
                p.setContacto(rs.getString("contacto"));
                p.setCorreoContacto(rs.getString("correoContacto"));
                p.setEstado(rs.getString("estado"));
                p.setIdUsuario(rs.getInt("idUsuario"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Busca un proveedor por su ID.
     */
    public proveedor buscarPorId(int idProveedor) {
        String sql = "SELECT * FROM PROVEEDOR WHERE idProveedor = ? AND estado = 'A'";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProveedor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                proveedor p = new proveedor();
                p.setIdProveedor(rs.getInt("idProveedor"));
                p.setRazonSocial(rs.getString("razonSocial"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo"));
                p.setDireccion(rs.getString("direccion"));
                p.setRuc(rs.getString("ruc"));
                p.setContacto(rs.getString("contacto"));
                p.setCorreoContacto(rs.getString("correoContacto"));
                p.setEstado(rs.getString("estado"));
                p.setIdUsuario(rs.getInt("idUsuario"));
                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
