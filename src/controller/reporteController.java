/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.reporteDao;
import model.reporteInventarioDTO;
import view.frmReporteInventario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class reporteController implements ActionListener {

   private frmReporteInventario vista;
    private reporteDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public reporteController(frmReporteInventario vista) {
        this.vista = vista;
        this.dao = new reporteDao();
        
        // 1. Escuchar el botón de la vista
        this.vista.getBotonImprimir().addActionListener(this);
        
        // 2. Llenar la tabla apenas se crea la ventana
        listar();
    }

    public void listar() {
        // ... (Mismo código de listar que te pasé antes) ...
        // Define columnas y llama al DAO
        modelo = new DefaultTableModel();
        modelo.addColumn("CÓDIGO");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("CATEGORÍA");
        modelo.addColumn("STOCK");
        modelo.addColumn("PRECIO");
        modelo.addColumn("PROVEEDOR");
        
        List<reporteInventarioDTO> lista = dao.obtenerReporteInventario();
        
        for (reporteInventarioDTO item : lista) {
            Object[] fila = new Object[6];
            fila[0] = item.getCodigo();
            fila[1] = item.getProducto();
            fila[2] = item.getCategoria();
            fila[3] = item.getStock();
            fila[4] = item.getPrecio();
            fila[5] = item.getProveedor();
            modelo.addRow(fila);
        }
        vista.getTablaInventario().setModel(modelo);
        // Aumentar la altura de las filas para que se lea mejor (más aire)
        vista.getTablaInventario().setRowHeight(30);

        // Ajustar anchos específicos (Indices: 0=Cod, 1=Prod, 2=Cat, 3=Stock, 4=Prec, 5=Prov)
        // Producto y Proveedor necesitan MUCHO espacio. Stock y Precio necesitan POCO.
        
        vista.getTablaInventario().getColumnModel().getColumn(0).setPreferredWidth(80);  // Código
        vista.getTablaInventario().getColumnModel().getColumn(1).setPreferredWidth(250); // Producto (ANCHO)
        vista.getTablaInventario().getColumnModel().getColumn(2).setPreferredWidth(120); // Categoría
        vista.getTablaInventario().getColumnModel().getColumn(3).setPreferredWidth(60);  // Stock (Angosto)
        vista.getTablaInventario().getColumnModel().getColumn(4).setPreferredWidth(60);  // Precio (Angosto)
        vista.getTablaInventario().getColumnModel().getColumn(5).setPreferredWidth(200); // Proveedor (ANCHO)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBotonImprimir()) {
            imprimir();
        }
    }

    private void imprimir() {
        try {
            // Imprime ajustando al ancho de la página (FIT_WIDTH)
            // Agregamos un pie de página con el número
            boolean completo = vista.getTablaInventario().print(JTable.PrintMode.FIT_WIDTH, 
                                     new MessageFormat("REPORTE DE INVENTARIO - BODEGAZACA"), 
                                     new MessageFormat("Página - {0}"));
            
            if (completo) {
                JOptionPane.showMessageDialog(null, "Impresión finalizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Impresión cancelada");
            }
            
         } catch (Exception ex) { 
             JOptionPane.showMessageDialog(null, "Error al imprimir: " + ex.getMessage());
             ex.printStackTrace(); 
         }
    }
}
