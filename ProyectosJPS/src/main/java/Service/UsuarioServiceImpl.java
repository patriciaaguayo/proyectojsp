/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.IUsuariosDAO;
import DAO.UsuariosDAOImpl;
import entities.Usuarios;


public class UsuarioServiceImpl implements UsuarioService {
    private IUsuariosDAO usuarioDAO = new UsuariosDAOImpl();

    @Override
    public boolean validarUsuario(String nombreUsuario) {
        return usuarioDAO.existeUsuario(nombreUsuario);
    }

    @Override
    public void registrarUsuario(Usuarios usuario) {
        usuarioDAO.registrarUsuario(usuario);
    }

    @Override
    public Usuarios usuarioEncontrado(String nombreUsuario) {
        return usuarioDAO.usuarioEncontrado(nombreUsuario);
    }
}
