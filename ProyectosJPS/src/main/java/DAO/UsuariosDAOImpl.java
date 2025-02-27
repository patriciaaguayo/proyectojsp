/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Util.HibernateUtil;
import entities.Usuarios;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UsuariosDAOImpl implements IUsuariosDAO {
    
    public UsuariosDAOImpl(){}  

   /**
     * Método para registrar un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto usuario a registrar.
     */
    @Override
    public void registrarUsuario(Usuarios usuario) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
            System.out.println("\n Usuario registrado exitosamente.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\n Error al registrar el usuario: " + e.getMessage());
        }
    }

    /**
     * Método booleano para verificar la existencia de un usuario por nombre y contraseña.
     *
     * @param nombreUsuario El nombre del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    
    @Override
    public boolean existeUsuario(String nombreUsuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Consulta SQL nativa
            String sql = "SELECT * FROM Usuarios WHERE Nombre_Usuario = :nombreUsuario";

            // Crear la consulta nativa
            Query query = session.createNativeQuery(sql, Usuarios.class);

            // Asignar parámetros
            query.setParameter("nombreUsuario", nombreUsuario);

            // Comprobar si hay resultados
            return !query.getResultList().isEmpty();

        } catch (Exception e) {
            System.out.println("\n Error al verificar la existencia del usuario: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param nombreUsuario El nombre del usuario.
     * @return El objeto Usuario si se encuentra, de lo contrario retorna null.
     */
    
    @Override
    public Usuarios usuarioEncontrado(String nombreUsuario) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        // Realizamos la consulta HQL correctamente
        return session.createQuery("FROM Usuarios WHERE nombreUsuario = :nombreUsuario", Usuarios.class)
                .setParameter("nombreUsuario", nombreUsuario) // Pasamos el parámetro
                .uniqueResult(); // Usamos uniqueResult() para obtener un solo resultado
    } catch (Exception e) {
        throw new RuntimeException("Error al buscar el usuario en la base de datos", e);
    }
}
}