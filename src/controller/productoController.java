/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.categoriaDao;
import dao.productoDao;
import dao.proveedorDao;
import model.categoria;
import model.producto;
import java.util.List;
import dao.loteDao;
import model.lote;

public class productoController {
    private productoDao productoDAO;
    private categoriaDao categoriaDAO;
    private proveedorDao proveedorDAO;
    private loteDao loteDAO;

    public productoController() {
        this.productoDAO = new productoDao();
        this.categoriaDAO = new categoriaDao();
        this.proveedorDAO = new proveedorDao();
        this.loteDAO = new loteDao(); // ✅ Inicializado
    }
    // Métodos públicos
public int obtenerIdProveedorPorNombre(String nombre) {
    return proveedorDAO.obtenerIdPorNombre(nombre);
}

public String obtenerNombreProveedor(int idProveedor) {
    return proveedorDAO.obtenerRazonSocialPorId(idProveedor);
}
public int obtenerIdCategoriaPorNombre(String nombre) {
    return categoriaDAO.obtenerIdPorNombre(nombre);
}
  public producto buscarProductoPorId(int idProducto) {
    return productoDAO.buscarPorId(idProducto);
}  

    // Métodos para Producto
    public boolean registrarProducto(producto producto) {
        return productoDAO.guardar(producto);
    }

    public boolean actualizarProducto(producto producto) {
        return productoDAO.actualizar(producto);
    }

    public boolean desactivarProducto(int idProducto) {
        return productoDAO.desactivar(idProducto);
    }

    public List<producto> obtenerProductos() {
        return productoDAO.listarTodos();
    }

    // Métodos para combos
    public List<String> obtenerCategorias() {
        return categoriaDAO.listarNombres();
    }

    public List<String> obtenerProveedores() {
        return proveedorDAO.listarNombres();
    }

    // Método para agregar nueva categoría
    public boolean registrarCategoria(String nombre, String descripcion, int idUsuario) {
        categoria c = new categoria();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setIdUsuario(idUsuario);
        return new categoriaDao().guardar(c);
    }
    
    public List<lote> obtenerLotesPorProducto(int idProducto) {
    return loteDAO.listarPorIdProducto(idProducto);
}

}
