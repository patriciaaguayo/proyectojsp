/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.ProyectosDAOImpl;
import DAO.TareasDAOImpl;
import Service.ProyectoService;
import Service.ProyectoServiceImpl;
import Service.TareaService;
import Service.TareaServiceImpl;
import Util.HibernateUtil;
import entities.Proyectos;
import entities.Tareas;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;

/**
 *
 * @author patriciaaguayo
 */
@WebServlet(name = "TareaServlet", urlPatterns = {"/TareaServlet"})
public class TareaServlet extends HttpServlet {
    
    private TareaServiceImpl tareaService;

    @Override
    public void init() throws ServletException {
        // Inicializar el servicio de tareas
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        tareaService = new TareaServiceImpl(sessionFactory);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TareaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TareaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProyecto = Integer.parseInt(request.getParameter("id")); // Obtener id del proyecto
        List<Tareas> tareas = tareaService.obtenerTareasPorProyecto(idProyecto);
        request.setAttribute("tareas", tareas); // Establecer las tareas en el request
        request.getRequestDispatcher("/JSP/Tareas.jsp").forward(request, response); // Redirigir a la página de tareas
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        if ("eliminar".equals(accion)) {
            eliminarTarea(request, response);
        } else {
            agregarTarea(request, response);  // Aquí se maneja el caso de agregar tarea
        }
    }
    
    private void eliminarTarea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener el id de la tarea que se quiere eliminar
            int idTarea = Integer.parseInt(request.getParameter("idTarea"));
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));

            // Llamamos al servicio para eliminar la tarea
            tareaService.eliminarTarea(idTarea);

            // Establecer un mensaje de éxito
            request.setAttribute("mensaje", "Tarea eliminada correctamente.");

        } catch (NumberFormatException e) {
            // En caso de error con el número del id
            request.setAttribute("mensaje", "Error: ID de tarea inválido.");
            e.printStackTrace();
        }

        // Redirigir al JSP de tareas con el ID del proyecto
        request.getRequestDispatcher("/JSP/Tareas.jsp?id=" + request.getParameter("idProyecto")).forward(request, response);
    }

    // Método para agregar tarea
    private void agregarTarea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descripcionTarea = request.getParameter("descripcionTarea");
        String responsable = request.getParameter("responsable");
        String fechaFinTareaStr = request.getParameter("fechaFinTarea");
        int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));

        // Convertir la fecha de fin de tarea a LocalDate
        LocalDate fechaFinTarea = null;
        if (fechaFinTareaStr != null && !fechaFinTareaStr.isEmpty()) {
            fechaFinTarea = LocalDate.parse(fechaFinTareaStr);
        }

        // Obtener el proyecto asociado al ID
        Proyectos proyecto = tareaService.obtenerProyectoPorId(idProyecto);

        // Verificar si se ha encontrado el proyecto
        if (proyecto != null) {
            // Crear la nueva tarea
            Tareas nuevaTarea = new Tareas(proyecto, descripcionTarea, responsable, "Pendiente", fechaFinTarea);

            // Agregar la tarea
            tareaService.agregarTarea(nuevaTarea);

            // Establecer un mensaje de éxito
            request.setAttribute("mensaje", "Tarea añadida correctamente.");
        } else {
            // En caso de que no se encuentre el proyecto
            request.setAttribute("mensaje", "Proyecto no encontrado.");
        }

        // Redirigir al JSP de tareas con el ID del proyecto
        request.getRequestDispatcher("/JSP/Tareas.jsp?id=" + idProyecto).forward(request, response);
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
