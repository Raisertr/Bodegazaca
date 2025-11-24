/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bodegazaca;

import org.mindrot.jbcrypt.BCrypt;
public class TestEncriptarContraseña {
     public static void main(String[] args) {
        // Contraseña original
        String contraseña = "2051";
        String hash = "$2a$10$XJf8nt5IuEtv0DWcZGQMaea6V5Hk7sCSq2xpO9baldb3qYILu9W8u"; // Tu hash real

        boolean valida = BCrypt.checkpw(contraseña, hash);
        System.out.println("¿La contraseña es válida? " + valida); // Debe decir true
    }
}


