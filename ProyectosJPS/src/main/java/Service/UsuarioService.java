/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import entities.Usuarios;

/**
 *
 * @author Patricia Aguayo
 */
public interface UsuarioService {
    boolean validarUsuario(String nombreUsuario);
    void registrarUsuario(Usuarios usuario);
    Usuarios usuarioEncontrado(String nombreUsuario);
}
