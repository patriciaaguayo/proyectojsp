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
    
    private SessionFactory sessionFactory;

    public ProyectosDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public ProyectosDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory(); // Obtener sessionFactory por defecto
    }

    @Override
    public List<Proyectos> obtenerProyectos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Proyectos", Proyectos.class).list();
        } catch (Exception e) {
            System.out.println("\n Error al obtener los proyectos: " + e.getMessage());
            return null;
        }
    }

    public void registrarProyecto(Proyectos proyecto) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(proyecto); // Guardar el proyecto
            transaction.commit(); // Confirmar la transacción
            System.out.println("\n✅ Proyecto registrado exitosamente.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.println("\n⚠️ Transacción revertida.");
            }
            System.out.println("\n❌ Error al registrar el proyecto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Proyectos> buscarPorEstado(String estadoProyecto) {
        try (Session session = sessionFactory.openSession()) {
            return session.createNamedQuery("Proyectos.findByEstadoProyecto", Proyectos.class)
                    .setParameter("estadoProyecto", estadoProyecto)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar proyecto en la base de datos", e);
        }
    }

    @Override
    public void eliminarProyectoPorId(Integer idProyecto) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Proyectos proyecto = session.get(Proyectos.class, idProyecto);
            if (proyecto != null) {
                session.delete(proyecto);
                System.out.println("\n✅ Proyecto eliminado exitosamente.");
            } else {
                System.out.println("\n⚠️ Proyecto no encontrado con el ID: " + idProyecto);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar proyecto por ID", e);
        }
    }
}


