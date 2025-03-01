/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Proyectos;
import entities.Tareas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class TareasDAOImpl implements ITareasDAO {

    private SessionFactory sessionFactory;

    // Constructor para inyectar la sessionFactory
    public TareasDAOImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("SessionFactory no puede ser nulo.");
        }
        this.sessionFactory = sessionFactory;
    }

    public TareasDAOImpl() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory debe ser inicializado.");
        }
    }

    @Override
    public List<Tareas> obtenerTareasPorProyecto(int idProyecto) {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory no está inicializada.");
        }

        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Tareas WHERE Id_Proyecto = :idProyecto";  // Usamos HQL en lugar de SQL nativo
            var query = session.createQuery(hql, Tareas.class);
            query.setParameter("idProyecto", idProyecto);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void agregarTarea(Tareas tarea) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(tarea);  // Guarda la tarea
            transaction.commit();  // Confirma la transacción
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // Revertir transacción en caso de error
            }
            throw new RuntimeException("Error al agregar la tarea", e);
        }
    }

    public void eliminarTarea(int idTarea) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Tareas tarea = session.get(Tareas.class, idTarea); // Buscar tarea por ID
            if (tarea != null) {
                session.delete(tarea);  // Elimina la tarea
            } else {
                throw new RuntimeException("Tarea no encontrada");
            }
            transaction.commit();  // Confirma la transacción
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // Revertir transacción en caso de error
            }
            throw new RuntimeException("Error al eliminar la tarea", e);
        }
    }

    @Override
    public Proyectos obtenerProyectoPorId(int idProyecto) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Proyectos.class, idProyecto); // Busca el proyecto por su ID
        } catch (Exception e) {
            System.err.println("Error al obtener el proyecto: " + e.getMessage());
            return null;
        }
    }
}

