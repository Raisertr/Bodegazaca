/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.detalleVenta;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class detalleVentaDao {
     public boolean guardar(detalleVenta detalle) {
        String sql = "INSERT INTO detalle_venta (cantidad, precio, subtotal, idVenta, idProducto, idLote) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalle.getCantidad());
            stmt.setBigDecimal(2, detalle.getPrecio());
            stmt.setBigDecimal(3, detalle.getSubtotal());
            stmt.setInt(4, detalle.getIdVenta());
            stmt.setInt(5, detalle.getIdProducto());
            stmt.setInt(6, detalle.getIdLote());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene todos los detalles de una venta.
     */
    public List<detalleVenta> obtenerPorIdVenta(int idVenta) {
        String sql = "SELECT * FROM detalle_venta WHERE idVenta = ?";
        List<detalleVenta> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                detalleVenta d = new detalleVenta();
                d.setIdDetalle(rs.getInt("idDetalle"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecio(rs.getBigDecimal("precio"));
                d.setSubtotal(rs.getBigDecimal("subTotal"));
                d.setIdVenta(rs.getInt("idVenta"));
                d.setIdProducto(rs.getInt("idProducto"));
                d.setIdLote(rs.getInt("idLote"));
                lista.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Actualiza el stock en la tabla LOTE.
     */
     public boolean actualizarStock(int idProducto, int cantidadVendida) {
        String sql = "UPDATE producto SET stock = stock - ? WHERE idProducto = ? AND stock >= ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cantidadVendida);
            stmt.setInt(2, idProducto);
            stmt.setInt(3, cantidadVendida);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
