/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.categoria;
import util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class categoriaDao {
      public List<String> listarNombres() {
        String sql = "SELECT nombre FROM categoria";
        List<String> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public int obtenerIdPorNombre(String nombre) {
    String sql = "SELECT idCategoria FROM categoria WHERE nombre = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nombre);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("idCategoria");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Si no encuentra
}
    public boolean guardar(categoria categoria) {
        String sql = "INSERT INTO categoria (nombre, descripcion, idUsuario) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setInt(3, categoria.getIdUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
