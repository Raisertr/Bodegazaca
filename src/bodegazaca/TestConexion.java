/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bodegazaca;

import java.sql.Connection;
import java.sql.SQLException;
import util.Conexion;
public class TestConexion {
    public static void main(String[] args) {
        System.out.println("Intentando conectar a la base de datos 'bodegazaca'...");

        try (Connection conn = Conexion.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ ¡Conexión exitosa a la base de datos!");
                System.out.println("Base de datos: bodegazaca");
                System.out.println("URL: jdbc:mysql://localhost:3306/bodegaZaca");
            } else {
                System.out.println("❌ No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a la base de datos:");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("Estado SQL: " + e.getSQLState());
            System.out.println("Código de error: " + e.getErrorCode());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado:");
            e.printStackTrace();
        }
    }
}
