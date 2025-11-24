/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.usuario;
import util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
public class usuarioDao {
      public usuario autenticar(String usuario, String contraseña) {
        String sql = "SELECT * FROM USUARIO WHERE usuario = ? AND estado = 'A'";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashGuardado = rs.getString("contraseña");

                // ✅ Validar usando BCrypt
                if (BCrypt.checkpw(contraseña, hashGuardado)) {
                    usuario u = new usuario();
                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setRol(rs.getString("rol"));
                    return u;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no coincide, retorna null
    }
      
      
      public boolean guardar(usuario usuario) {
    String sql = "INSERT INTO USUARIO (nombre, usuario, contraseña, rol, estado) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, usuario.getNombre());
        stmt.setString(2, usuario.getUsuario());
         stmt.setString(3, usuario.getContraseña());
        stmt.setString(4, usuario.getRol());
        stmt.setString(5, usuario.getEstado());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
      
      public boolean desactivar(int idUsuario) {
    String sql = "UPDATE USUARIO SET estado = 'I' WHERE idUsuario = ?";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);
        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
      public List<usuario> listarActivos() {
    String sql = "SELECT idUsuario, nombre, usuario, rol FROM USUARIO WHERE estado = 'A'";
    List<usuario> lista = new ArrayList<>();

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            usuario u = new usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNombre(rs.getString("nombre"));
            u.setUsuario(rs.getString("usuario"));
            u.setRol(rs.getString("rol"));
            lista.add(u);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
}
