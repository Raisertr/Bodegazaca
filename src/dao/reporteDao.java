/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.Conexion; // Asegúrate de importar tu clase de conexión correctamente
import model.reporteInventarioDTO;
import model.topProductoDTO;
import model.vencimientoDTO;
public class reporteDao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<reporteInventarioDTO> obtenerReporteInventario() {
        List<reporteInventarioDTO> lista = new ArrayList<>();
        // El SQL con los JOINS para traer nombres en vez de IDs
        String sql = "SELECT p.codigoBarras, p.nombre, c.nombre AS cat, p.stock, p.precio, pr.razonSocial " +
                     "FROM producto p " +
                     "INNER JOIN categoria c ON p.idCategoria = c.idCategoria " +
                     "LEFT JOIN proveedor pr ON p.idProveedor = pr.idProveedor " +
                     "WHERE p.estado = 'A' ORDER BY p.nombre ASC";
        
        try {
            con = Conexion.getConnection(); // Ajusta según cómo llames a tu conexión estática
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                reporteInventarioDTO fila = new reporteInventarioDTO();
                fila.setCodigo(rs.getString("codigoBarras"));
                fila.setProducto(rs.getString("nombre"));
                fila.setCategoria(rs.getString("cat")); // El alias que pusimos en SQL
                fila.setStock(rs.getInt("stock"));
                fila.setPrecio(rs.getDouble("precio"));
                // Manejo de nulos por si no tiene proveedor (LEFT JOIN)
                String prov = rs.getString("razonSocial");
                fila.setProveedor(prov != null ? prov : "Sin Proveedor");
                
                lista.add(fila);
            }
        } catch (Exception e) {
            System.out.println("Error en ReporteDAO: " + e.toString());
        }
        return lista;
    }
    
    public List<topProductoDTO> obtenerTopProductos() {
        List<topProductoDTO> lista = new ArrayList<>();
        
        // SQL EXPLICADO:
        // 1. Sumamos la cantidad (SUM(d.cantidad))
        // 2. Filtramos solo ventas activas (WHERE v.estado = 'Activo')
        // 3. Agrupamos por producto para que sume los totales
        // 4. Ordenamos DESCENDENTE (DESC) para ver los líderes arriba
        
        String sql = "SELECT p.codigoBarras, p.nombre, c.nombre AS cat, SUM(d.cantidad) as total_vendido " +
                     "FROM detalle_venta d " +
                     "INNER JOIN venta v ON d.idVenta = v.idVenta " +
                     "INNER JOIN producto p ON d.idProducto = p.idProducto " +
                     "INNER JOIN categoria c ON p.idCategoria = c.idCategoria " +
                     "WHERE v.estado = 'Activo' " +
                     "GROUP BY p.idProducto, p.codigoBarras, p.nombre, c.nombre " +
                     "ORDER BY total_vendido DESC";
        
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                topProductoDTO fila = new topProductoDTO();
                fila.setCodigo(rs.getString("codigoBarras"));
                fila.setProducto(rs.getString("nombre"));
                fila.setCategoria(rs.getString("cat"));
                fila.setCantidadTotal(rs.getInt("total_vendido"));
                
                lista.add(fila);
            }
        } catch (Exception e) {
            System.out.println("Error en TopProductos: " + e.toString());
        }
        return lista;
    }
    
    public List<vencimientoDTO> obtenerProximosVencer() {
        List<vencimientoDTO> lista = new ArrayList<>();
        
        // SQL: Traemos datos y calculamos 'dias_restantes'
        // Filtramos (WHERE cantidad > 0) para ver solo lo que tenemos en físico
        String sql = "SELECT p.codigoBarras, p.nombre, l.numeroLote, l.fechaVencimiento, " +
                     "l.cantidad, pr.razonSocial, " +
                     "DATEDIFF(l.fechaVencimiento, CURDATE()) as dias_restantes " +
                     "FROM lote l " +
                     "INNER JOIN producto p ON l.idProducto = p.idProducto " +
                     "LEFT JOIN proveedor pr ON p.idProveedor = pr.idProveedor " +
                     "WHERE l.cantidad > 0 " +
                     "ORDER BY l.fechaVencimiento ASC"; 
                     // ASC pone las fechas más antiguas (o próximas) primero
        
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                vencimientoDTO fila = new vencimientoDTO();
                fila.setCodigo(rs.getString("codigoBarras"));
                fila.setProducto(rs.getString("nombre"));
                fila.setNumeroLote(rs.getString("numeroLote"));
                fila.setFechaVencimiento(rs.getDate("fechaVencimiento"));
                fila.setStockActual(rs.getInt("cantidad"));
                
                String prov = rs.getString("razonSocial");
                fila.setProveedor(prov != null ? prov : "Sin Prov.");
                
                fila.setDiasRestantes(rs.getInt("dias_restantes"));
                
                lista.add(fila);
            }
        } catch (Exception e) {
            System.out.println("Error en ProximosVencer: " + e.toString());
        }
        return lista;
    }
}
