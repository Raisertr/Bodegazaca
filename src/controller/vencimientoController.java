/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.reporteDao;
import model.vencimientoDTO;
import view.frmVencimientos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class vencimientoController implements ActionListener {

    private frmVencimientos vista;
    private reporteDao dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public vencimientoController(frmVencimientos vista) {
        this.vista = vista;
        this.dao = new reporteDao();
        
        this.vista.getBotonImprimir().addActionListener(this);
        
        listarVencimientos();
    }

    public void listarVencimientos() {
        modelo = new DefaultTableModel();
        modelo.addColumn("ESTADO"); // Columna nueva de alerta
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("LOTE");
        modelo.addColumn("VENCE EL");
        modelo.addColumn("DÍAS REST.");
        modelo.addColumn("STOCK LOTE");
        modelo.addColumn("PROVEEDOR");
        
        List<vencimientoDTO> lista = dao.obtenerProximosVencer();
        
        for (vencimientoDTO item : lista) {
            Object[] fila = new Object[7];
            
            // LOGICA DE SEMÁFORO (Texto)
            int dias = item.getDiasRestantes();
            String estado = "";
            
            if (dias < 0) {
                estado = "!!! VENCIDO !!!";
            } else if (dias <= 30) {
                estado = "URGENTE"; // Menos de un mes
            } else if (dias <= 60) {
                estado = "ATENCIÓN"; // Entre 1 y 2 meses
            } else {
                estado = "OK";
            }
            
            fila[0] = estado;
            fila[1] = item.getProducto();
            fila[2] = item.getNumeroLote();
            fila[3] = item.getFechaVencimiento();
            fila[4] = dias; // Número de días
            fila[5] = item.getStockActual();
            fila[6] = item.getProveedor();
            
            modelo.addRow(fila);
        }
        
        vista.getTablaVencimiento().setModel(modelo);

        // --- DISEÑO VISUAL ---
        vista.getTablaVencimiento().setRowHeight(30);
        
        // Estado (Importante ver la alerta)
        vista.getTablaVencimiento().getColumnModel().getColumn(0).setPreferredWidth(90);
        // Producto (Ancho)
        vista.getTablaVencimiento().getColumnModel().getColumn(1).setPreferredWidth(200);
        // Lote
        vista.getTablaVencimiento().getColumnModel().getColumn(2).setPreferredWidth(80);
        // Fecha
        vista.getTablaVencimiento().getColumnModel().getColumn(3).setPreferredWidth(80);
        // Días (Pequeño)
        vista.getTablaVencimiento().getColumnModel().getColumn(4).setPreferredWidth(60);
        // Stock (Pequeño)
        vista.getTablaVencimiento().getColumnModel().getColumn(5).setPreferredWidth(60);
        // Proveedor (Ancho)
        vista.getTablaVencimiento().getColumnModel().getColumn(6).setPreferredWidth(150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBotonImprimir()) {
            imprimir();
        }
    }

    private void imprimir() {
         try {
            boolean completo = vista.getTablaVencimiento().print(JTable.PrintMode.FIT_WIDTH, 
                                     new MessageFormat("REPORTE DE VENCIMIENTOS POR LOTE"), 
                                     new MessageFormat("Página - {0}"));
         } catch (Exception ex) { 
             JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
         }
    }
}
