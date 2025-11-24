/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.producto;
import util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class productoDao {
private String generarSKU() {
    // 1. Buscamos el valor NUMÉRICO más alto que exista actualmente en la columna 'sku'
    // Usamos CAST porque en tu base de datos el sku es VARCHAR
    String sql = "SELECT MAX(CAST(sku AS UNSIGNED)) FROM producto";
    
    // Queremos empezar desde aquí
    int base = 10000; 

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            // Obtiene el SKU más alto que existe hoy. Ej: Si tienes "10005", trae 10005.
            // Si tienes "2" (datos basura), trae 2.
            int ultimoSku = rs.getInt(1);
            
            // LÓGICA DE CORRECCIÓN:
            // Si el último SKU es 0 (tabla vacía) O es menor que la base (ej: tienes guardado un "3")
            // Forzamos a que el siguiente sea 10001.
            if (ultimoSku < base) {
                return String.valueOf(base + 1); // Retorna "10001"
            } else {
                return String.valueOf(ultimoSku + 1); // Retorna "10002", "10003", etc.
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Fallback por si falla la conexión
    return String.valueOf(base + 1);
}
public producto buscarPorId(int idProducto) {
    String sql = "SELECT p.*, c.nombre AS nombreCategoria FROM PRODUCTO p LEFT JOIN CATEGORIA c ON p.idCategoria = c.idCategoria WHERE idProducto = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idProducto);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            producto p = new producto();
            p.setIdProducto(rs.getInt("idProducto"));
            p.setNombre(rs.getString("nombre"));
            p.setCodigoBarras(rs.getString("codigoBarras"));
            p.setCategoria(rs.getString("idCategoria"));
            p.setCategoria(rs.getString("nombreCategoria"));
            p.setPrecio(rs.getBigDecimal("precio"));
            p.setCosto(rs.getDouble("costo"));
            p.setStock(rs.getInt("stock"));
            p.setIdProveedor(rs.getInt("idProveedor"));
            p.setIdUsuario(rs.getInt("idUsuario"));
            p.setEstado(rs.getString("estado"));
            p.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
            return p;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public boolean guardar(producto producto) {
        String sku = generarSKU();
        producto.setSku(sku);

        String sql = "INSERT INTO producto (sku, codigoBarras, nombre, idCategoria, precio, costo, stock, idProveedor, idUsuario, estado, fechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getSku());
            stmt.setString(2, producto.getCodigoBarras());
            stmt.setString(3, producto.getNombre());
            stmt.setInt(4, producto.getIdCategoria());
            stmt.setBigDecimal(5, producto.getPrecio());
            stmt.setDouble(6, producto.getCosto());
            stmt.setInt(7, producto.getStock());
            stmt.setInt(8, producto.getIdProveedor());
            stmt.setInt(9, producto.getIdUsuario());
            stmt.setString(10, producto.getEstado());
            stmt.setTimestamp(11, new java.sql.Timestamp(producto.getFechaRegistro().getTime()));

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(producto producto) {
        String sql = "UPDATE PRODUCTO SET nombre = ?, codigoBarras = ?, idCategoria = ?, precio = ?, costo = ?, stock = ?, idProveedor = ? WHERE idProducto = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCodigoBarras());
            stmt.setInt(3, producto.getIdCategoria());
            stmt.setBigDecimal(4, producto.getPrecio());
            stmt.setDouble(5, producto.getCosto());
            stmt.setInt(6, producto.getStock());
            stmt.setInt(7, producto.getIdProveedor());
            stmt.setInt(8, producto.getIdProducto());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean desactivar(int idProducto) {
        String sql = "UPDATE PRODUCTO SET estado = 'I' WHERE idProducto = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<producto> listarTodos() {
        String sql = "SELECT p.*, c.nombre AS nombreCategoria FROM PRODUCTO p LEFT JOIN CATEGORIA c ON p.idCategoria = c.idCategoria WHERE p.estado = 'A'";
        List<producto> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                producto p = new producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setSku(rs.getString("sku"));
                p.setCodigoBarras(rs.getString("codigoBarras"));
                p.setNombre(rs.getString("nombre"));
                p.setCategoria(rs.getString("nombreCategoria")); 
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setPrecio(rs.getBigDecimal("precio"));
                p.setCosto(rs.getDouble("costo"));
                p.setStock(rs.getInt("stock"));
                p.setIdProveedor(rs.getInt("idProveedor"));
                p.setIdUsuario(rs.getInt("idUsuario"));
                p.setEstado(rs.getString("estado"));
                p.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
