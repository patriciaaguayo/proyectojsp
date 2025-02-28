/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entities.Proyectos;
import java.util.List;

/**
 *
 * @author Patricia Aguayo
 */
public interface IProyectosDAO {
    
    List<Proyectos> obtenerProyectos();
    
    public void registrarProyecto(Proyectos proyecto);
     
    public List<Proyectos> buscarPorEstado(String estadoProyecto);
    
    // Método para eliminar un proyecto por su ID
    void eliminarProyectoPorId(Integer idProyecto);
}
