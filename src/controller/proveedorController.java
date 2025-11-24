/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.proveedorDao;
import model.proveedor;
import java.util.List;
        
        
    public class proveedorController {
      private proveedorDao proveedorDAO;

    public proveedorController() {
        this.proveedorDAO = new proveedorDao();
    }

    /**
     * Registra un nuevo proveedor.
     */
    public boolean registrarProveedor(proveedor proveedor) {
        return proveedorDAO.guardar(proveedor);
    }

    /**
     * Actualiza un proveedor existente.
     */
    public boolean actualizarProveedor(proveedor proveedor) {
        return proveedorDAO.actualizar(proveedor);
    }

    /**
     * Desactiva un proveedor por su ID.
     */
    public boolean desactivarProveedor(int idProveedor) {
        return proveedorDAO.desactivar(idProveedor);
    }

    /**
     * Obtiene todos los proveedores activos.
     */
    public List<proveedor> obtenerProveedoresActivos() {
        return proveedorDAO.listarActivos();
    }

    /**
     * Busca un proveedor por su ID.
     */
    public proveedor buscarProveedorPorId(int idProveedor) {
        return proveedorDAO.buscarPorId(idProveedor);
    }
}
