/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.reporteDao;
import model.topProductoDTO;
import view.frmTopProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class topProductosController implements ActionListener {

    private frmTopProductos vista;
    private reporteDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public topProductosController(frmTopProductos vista) {
        this.vista = vista;
        this.dao = new reporteDao();
        
        this.vista.getBotonImprimir().addActionListener(this);
        
        // Cargar datos al iniciar
        listarRanking();
    }

    public void listarRanking() {
        modelo = new DefaultTableModel();
        modelo.addColumn("PUESTO"); // Agregamos columna de ranking (1°, 2°, 3°...)
        modelo.addColumn("CÓDIGO");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("CATEGORÍA");
        modelo.addColumn("TOTAL VENDIDO (Unidades)");
        
        List<topProductoDTO> lista = dao.obtenerTopProductos();
        
        int puesto = 1;
        for (topProductoDTO item : lista) {
            Object[] fila = new Object[5];
            fila[0] = puesto + "°"; // Ejemplo: 1°
            fila[1] = item.getCodigo();
            fila[2] = item.getProducto();
            fila[3] = item.getCategoria();
            fila[4] = item.getCantidadTotal();
            modelo.addRow(fila);
            puesto++;
        }
        
        vista.getTablaTop().setModel(modelo);

        // --- FORMATO VISUAL (Para que se vea profesional) ---
        vista.getTablaTop().setRowHeight(30); // Filas más altas
        
        // Puesto (angosto)
        vista.getTablaTop().getColumnModel().getColumn(0).setPreferredWidth(50);
        // Código
        vista.getTablaTop().getColumnModel().getColumn(1).setPreferredWidth(80);
        // Producto (ANCHO)
        vista.getTablaTop().getColumnModel().getColumn(2).setPreferredWidth(250);
        // Categoría
        vista.getTablaTop().getColumnModel().getColumn(3).setPreferredWidth(120);
        // Total (angosto)
        vista.getTablaTop().getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBotonImprimir()) {
            imprimir();
        }
    }

    private void imprimir() {
         try {
            boolean completo = vista.getTablaTop().print(JTable.PrintMode.FIT_WIDTH, 
                                     new MessageFormat("TOP PRODUCTOS MÁS VENDIDOS - BODEGAZACA"), 
                                     new MessageFormat("Página - {0}"));
            
         } catch (Exception ex) { 
             JOptionPane.showMessageDialog(null, "Error al imprimir: " + ex.getMessage());
         }
    }
}
