/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.ITareasDAO;
import DAO.TareasDAOImpl;
import entities.Proyectos;
import entities.Tareas;
import java.util.List;
import org.hibernate.SessionFactory;


public class TareaServiceImpl implements TareaService {

    private ITareasDAO tareasDAO; 
    
    public TareaServiceImpl(){
        tareasDAO = new TareasDAOImpl();
    }

    public TareaServiceImpl(SessionFactory sessionFactory) {
        this.tareasDAO = new TareasDAOImpl(sessionFactory);
    }

    @Override
    public List<Tareas> obtenerTareasPorProyecto(int idProyecto) {
        List<Tareas> tareas = tareasDAO.obtenerTareasPorProyecto(idProyecto);
        System.out.println("Número de tareas encontradas para el proyecto " + idProyecto + ": " + tareas.size());
        return tareas;
    }

    @Override
    public void agregarTarea(Tareas tarea) {
        try {
            // Llamamos al DAO para agregar la tarea
            tareasDAO.agregarTarea(tarea);
        } catch (Exception e) {
            // Si ocurre un error, lanzamos una excepción
            throw new RuntimeException("No se pudo agregar la tarea", e);
        }
    }

    @Override
    public void eliminarTarea(int idTarea) {
        try {
            // Llamamos al DAO para eliminar la tarea
            tareasDAO.eliminarTarea(idTarea);
        } catch (Exception e) {
            // Si ocurre un error, lanzamos una excepción
            throw new RuntimeException("No se pudo eliminar la tarea", e);
        }
    }
    
    @Override
    public Proyectos obtenerProyectoPorId(int idProyecto){
        return tareasDAO.obtenerProyectoPorId(idProyecto);
    }
}
