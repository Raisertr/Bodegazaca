/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexion {
    // 1. Constantes de conexión (Igual que antes)
    private static final String URL = "jdbc:mysql://localhost:3306/bodegaZaca";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    // 2. PATRÓN SINGLETON: Variable estática para guardar la ÚNICA conexión
    private static Connection instancia = null;

    // 3. Constructor privado para evitar que alguien haga "new Conexion()"
    private Conexion() { }

    // 4. Método estático modificado
    public static Connection getConnection() throws SQLException {
        try {
            // LÓGICA DEL PATRÓN SINGLETON:
            // Preguntamos: ¿La conexión no existe? O ¿Se cerró por error?
            if (instancia == null || instancia.isClosed()) {
                
                // Solo si es necesario, creamos la conexión nueva
                Class.forName("com.mysql.cj.jdbc.Driver");
                instancia = DriverManager.getConnection(URL, USER, PASSWORD);
                // System.out.println("Conexión creada exitosamente"); // Opcional: Para probar
            }
            
            // Si ya existía, simplemente devolvemos la que ya estaba viva
            return instancia;
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver de MySQL", e);
        }
    }
}
