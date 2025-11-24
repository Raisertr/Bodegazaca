/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.detalleVentaDao;
import dao.ventaDao;
import model.detalleVenta;
import model.venta;
import java.util.List;
public class ventaController {
    private ventaDao ventaDAO;
    private detalleVentaDao detalleVentaDAO;

    public ventaController() {
        this.ventaDAO = new ventaDao();
        this.detalleVentaDAO = new detalleVentaDao();
    }

    public boolean registrarVenta(venta venta) {
         boolean exito = false;
    try {
        exito = ventaDAO.guardar(venta);
        if (exito && venta.getDetalles() != null) {
            for (detalleVenta d : venta.getDetalles()) {
                d.setIdVenta(venta.getIdVenta());
                d.calcularSubtotal(); // Calcula el subtotal
                boolean detalleExito = detalleVentaDAO.guardar(d);
                if (!detalleExito) {
                    return false;
                }
                // Actualiza el stock en PRODUCTO
                boolean stockExito = detalleVentaDAO.actualizarStock(d.getIdProducto(), d.getCantidad());
                if (!stockExito) {
                    return false;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    return exito;
    }

    public List<venta> obtenerVentas() {
        return ventaDAO.listarTodos();
    }
}
