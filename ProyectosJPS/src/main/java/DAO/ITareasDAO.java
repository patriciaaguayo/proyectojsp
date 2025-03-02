/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entities.Proyectos;
import entities.Tareas;
import java.util.List;

/**
 *
 * @author Patricia Aguayo
 */
public interface ITareasDAO {
    
    List<Tareas> obtenerTareasPorProyecto(int idProyecto);
    void agregarTarea(Tareas tarea);
    void eliminarTarea(int idTarea);
    Proyectos obtenerProyectoPorId(int idProyecto);
    Tareas obtenerTareaPorId(int idTarea);
}
