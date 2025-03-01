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
import org.hibernate.Transaction;

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

    /**
     * @param proyecto se le pasa el autor a registrar
     */
    
    public void registrarProyecto(Proyectos proyecto) {
        Transaction transaction = null;
        Session session = null;

        System.out.println("Comenzando la transacción...");
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            System.out.println("Guardando el proyecto...");
            session.save(proyecto); // Guardar el proyecto

            transaction.commit(); // Confirmar la transacción
            System.out.println("\n✅ Proyecto registrado exitosamente.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                    System.out.println("\n⚠️ Transacción revertida.");
                } catch (Exception rollbackEx) {
                    System.out.println("\n❌ Error al hacer rollback: " + rollbackEx.getMessage());
                }
            }
            System.out.println("\n❌ Error al registrar el proyecto: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public List<Proyectos> buscarPorEstado(String estadoProyecto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Proyectos.findByEstadoProyecto", Proyectos.class)
                    .setParameter("estadoProyecto", estadoProyecto)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar proyecto en la base de datos", e);
        }
    }
    
    @Override
    public void eliminarProyectoPorId(Integer idProyecto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            
            // Buscar el proyecto por su ID
            Proyectos proyecto = session.get(Proyectos.class, idProyecto);
            
            // Si el proyecto existe, eliminarlo
            if (proyecto != null) {
                session.delete(proyecto);
            }
            
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar proyecto por ID", e);
        }
    }
    
}
