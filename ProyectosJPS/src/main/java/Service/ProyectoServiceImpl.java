/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.ProyectosDAOImpl;
import entities.Proyectos;
import java.util.List;


public class ProyectoServiceImpl implements ProyectoService {

     private ProyectosDAOImpl proyectoDAO;
     
     public ProyectoServiceImpl(ProyectosDAOImpl proyectoDAO) {
        this.proyectoDAO = proyectoDAO;
    }
     
    public ProyectoServiceImpl(){}
    
    @Override
    public List<Proyectos> obtenerProyectos() {
        return proyectoDAO.obtenerProyectos(); // Este método debería devolver todos los proyectos.
    }

    @Override
    public void insertarProyecto(Proyectos proyecto) {
        proyectoDAO.registrarProyecto(proyecto); // Este método debería insertar el proyecto en la base de datos.
    }

    @Override
    public List<Proyectos> buscarPorEstado(String estadoProyecto) {
        return proyectoDAO.buscarPorEstado(estadoProyecto); 
    }
    
    @Override
    public void eliminarProyectoPorId(Integer idProyecto) {
        proyectoDAO.eliminarProyectoPorId(idProyecto); 
    }
    
}
