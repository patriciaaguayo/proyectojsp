/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entities.Usuarios;

/**
 *
 * @author Patricia Aguayo
 */
public interface IUsuariosDAO {
    
    /**
     * Método para registrar un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto usuario a registrar.
     */
    
    public void registrarUsuario(Usuarios usuario);
    
    /**
     * Método booleano para verificar la existencia de un usuario por nombre y contraseña.
     *
     * @param nombreUsuario El nombre del usuario.
     * @param password La contraseña del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean existeUsuario(String nombreUsuario, String password);
    
}
