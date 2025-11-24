/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.lote;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
        
public class loteDao {
    /**
     * Guarda un nuevo lote.
     */
    public boolean guardar(lote lote) {
        String sql = "INSERT INTO lote (numeroLote, fechaVencimiento, cantidad, fechaIngreso, idProducto, idUsuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lote.getNumeroLote());
            stmt.setDate(2, new java.sql.Date(lote.getFechaVencimiento().getTime()));
            stmt.setInt(3, lote.getCantidad());
            stmt.setTimestamp(4, new java.sql.Timestamp(lote.getFechaIngreso().getTime()));
            stmt.setInt(5, lote.getIdProducto());
            stmt.setInt(6, lote.getIdUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<lote> listarPorIdProducto(int idProducto) {
    String sql = "SELECT * FROM LOTE WHERE idProducto = ?";
    List<lote> lista = new ArrayList<>();
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idProducto);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            lote l = new lote();
            l.setIdLote(rs.getInt("idLote"));
            l.setNumeroLote(rs.getString("numeroLote"));
            l.setFechaVencimiento(rs.getDate("fechaVencimiento"));
            l.setCantidad(rs.getInt("cantidad"));
            l.setFechaIngreso(rs.getTimestamp("fechaIngreso"));
            l.setIdProducto(rs.getInt("idProducto"));
            l.setIdUsuario(rs.getInt("idUsuario"));
            lista.add(l);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}
 


    /**
     * Obtiene todos los lotes activos.
     */
    public List<lote> listarTodos() {
        String sql = "SELECT * FROM lote";
        List<lote> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lote l = new lote();
                l.setIdLote(rs.getInt("idLote"));
                l.setNumeroLote(rs.getString("numeroLote"));
                l.setFechaVencimiento(rs.getDate("fechaVencimiento"));
                l.setCantidad(rs.getInt("cantidad"));
                l.setFechaIngreso(rs.getTimestamp("fechaIngreso"));
                l.setIdProducto(rs.getInt("idProducto"));
                l.setIdUsuario(rs.getInt("idUsuario"));
                lista.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Anula un lote (elimina o desactiva).
     */
     public boolean anular(int idLote) {
        String sql = "DELETE FROM lote WHERE idLote = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLote);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
