/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import entities.Proyectos;
import entities.Tareas;
import java.util.List;

/**
 *
 * @author patriciaaguayo
 */
public interface TareaService {
    
    // Obtener tareas por proyecto
    List<Tareas> obtenerTareasPorProyecto(int idProyecto);

    // Añadir tarea
    void agregarTarea(Tareas tarea);

    // Eliminar tarea
    void eliminarTarea(int idTarea);
    
    Proyectos obtenerProyectoPorId(int idProyecto);
    
    Tareas obtenerTareaPorId(int idTarea);
}
