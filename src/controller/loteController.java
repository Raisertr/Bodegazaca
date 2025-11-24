/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.loteDao;
import model.lote;
import java.util.List;

public class loteController {
    private loteDao loteDao;

    public loteController() {
        this.loteDao = new loteDao();
    }

    // Métodos
    public boolean registrarLote(lote lote) {
        return loteDao.guardar(lote);
    }

    public boolean anularLote(int idLote) {
        return loteDao.anular(idLote);
    }

    public List<lote> obtenerLotes() {
        return loteDao.listarTodos();
    }
}
