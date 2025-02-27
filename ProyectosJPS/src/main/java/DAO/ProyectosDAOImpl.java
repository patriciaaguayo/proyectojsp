/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Util.HibernateUtil;
import entities.Proyectos;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProyectosDAOImpl implements IProyectosDAO {
    
    public ProyectosDAOImpl(SessionFactory sessionFactory){};
    
    public ProyectosDAOImpl(){};

    @Override
    public List<Proyectos> obtenerProyectos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("FROM Proyectos", Proyectos.class).list();

        } catch (Exception e) {
            System.out.println("\n Error al obtener los proyectos: " + e.getMessage());
            return null;
        }
    }
    
}
