/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import entities.Proyectos;
import java.util.List;

/**
 *
 * @author patriciaaguayo
 */
public interface ProyectoService {
    
   // Método para obtener todos los proyectos
    public List<Proyectos> obtenerProyectos();
    
    // Método para insertar un proyecto
    public void insertarProyecto(Proyectos proyecto);
    
    // Método para buscar proyectos por estado
    public List<Proyectos> buscarPorEstado(String estadoProyecto);
    
    // Método para eliminar un proyecto por id
    public void eliminarProyectoPorId(Integer idProyecto);
}
