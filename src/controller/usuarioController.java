/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.usuarioDao;
import model.usuario;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;
public class usuarioController {
    private usuarioDao usuarioDao;
    public usuarioController() {
        this.usuarioDao = new usuarioDao();
    }

    /**
     * Registra un nuevo usuario (con contraseña encriptada)
     */
    public boolean registrarUsuario(String nombre, String usuario, String contraseña, String rol) {
        try {
            // Encriptar la contraseña
            String hash = BCrypt.hashpw(contraseña, BCrypt.gensalt());

            // Crear objeto Usuario
            usuario u = new usuario();
            u.setNombre(nombre);
            u.setUsuario(usuario);
            u.setContraseña(hash);
            u.setRol(rol);
            u.setEstado("A");

            // Guardar en la base de datos
            return usuarioDao.guardar(u);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Desactiva un usuario por su ID
     */
    public boolean desactivarUsuario(int idUsuario) {
        return usuarioDao.desactivar(idUsuario);
    }

    /**
     * Obtiene todos los usuarios activos
     */
    public List<usuario> obtenerUsuariosActivos() {
        return usuarioDao.listarActivos();
    }
}
