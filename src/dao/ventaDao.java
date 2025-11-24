/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.venta;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ventaDao {
      public boolean guardar(venta venta) {
        String sql = "INSERT INTO venta (numeroDocumento, fecha, subTotal, igv, total, idUsuario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, venta.getNumeroDocumento());
            stmt.setTimestamp(2, new java.sql.Timestamp(venta.getFecha().getTime()));
            stmt.setBigDecimal(3, venta.getSubTotal());
            stmt.setBigDecimal(4, venta.getIgv());
            stmt.setBigDecimal(5, venta.getTotal());
            stmt.setInt(6, venta.getIdUsuario());
            stmt.setString(7, venta.getEstado());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    venta.setIdVenta(generatedKeys.getInt(1));
                }
            }

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene todas las ventas activas.
     */
    public List<venta> listarTodos() {
        String sql = "SELECT * FROM venta WHERE estado = 'Activo'";
        List<venta> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                venta v = new venta();
                v.setIdVenta(rs.getInt("idVenta"));
                v.setNumeroDocumento(rs.getString("numeroDocumento"));
                v.setFecha(rs.getTimestamp("fecha"));
                v.setSubTotal(rs.getBigDecimal("subTotal"));
                v.setIgv(rs.getBigDecimal("igv"));
                v.setTotal(rs.getBigDecimal("total"));
                v.setIdUsuario(rs.getInt("idUsuario"));
                v.setEstado(rs.getString("estado"));
                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
