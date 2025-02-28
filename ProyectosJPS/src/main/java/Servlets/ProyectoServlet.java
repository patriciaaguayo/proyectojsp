/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.IProyectosDAO;
import DAO.ProyectosDAOImpl;
import Service.ProyectoServiceImpl;
import Util.HibernateUtil;
import entities.Proyectos;
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
@WebServlet(name = "ProyectoServlet", urlPatterns = {"/ProyectoServlet"})
public class ProyectoServlet extends HttpServlet {
    
    private ProyectoServiceImpl proyectoService;

    @Override
    public void init() throws ServletException {
        // Inicializar ProyectoService
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ProyectosDAOImpl proyectoDAO = new ProyectosDAOImpl(sessionFactory);
        proyectoService = new ProyectoServiceImpl(proyectoDAO);
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
            out.println("<title>Servlet ProyectoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProyectoServlet at " + request.getContextPath() + "</h1>");
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
        String estadoProyecto = request.getParameter("estado"); // Obtener el estado filtrado

        List<Proyectos> proyectos;
        
        if (estadoProyecto != null && !estadoProyecto.isEmpty()) {
            proyectos = proyectoService.buscarPorEstado(estadoProyecto); // Filtrar por estado
        } else {
            proyectos = proyectoService.obtenerProyectos(); // Obtener todos los proyectos
        }

        // Establecer los proyectos en el request
        request.setAttribute("proyectos", proyectos);

        // Redirigir a la JSP
        request.getRequestDispatcher("/JSP/Proyectos.jsp").forward(request, response);
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
        String nombreProyecto = request.getParameter("nombreProyecto");
        String descripcionProyecto = request.getParameter("descripcionProyecto");
        String fechaFin = request.getParameter("fechaFin");
        String idProyecto = request.getParameter("idProyecto");

        // Si se está añadiendo un nuevo proyecto
        if (nombreProyecto != null && descripcionProyecto != null) {
            Proyectos nuevoProyecto = new Proyectos();
            nuevoProyecto.setNombre_Proyecto(nombreProyecto);
            nuevoProyecto.setDescripcion_Proyecto(descripcionProyecto);
            nuevoProyecto.setFecha_Fin_Proyecto(LocalDate.parse(fechaFin));

            proyectoService.insertarProyecto(nuevoProyecto);
        }

        // Si se está eliminando un proyecto
        if (idProyecto != null && !idProyecto.isEmpty()) {
            Integer id = Integer.parseInt(idProyecto);
            proyectoService.eliminarProyectoPorId(id);
        }

        // Redirigir después de la acción
        response.sendRedirect("ProyectoServlet");
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
