/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Service.UsuarioService;
import Service.UsuarioServiceImpl;
import entities.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author patriciaaguayo
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

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
            out.println("<title>Servlet UsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        String action = request.getParameter("action");
        String username = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");

        if ("login".equals(action)) {
            // Validar login
            UsuarioService usuarioService = new UsuarioServiceImpl();
            Usuarios usuario = usuarioService.usuarioEncontrado(username);

            if (usuario != null) {
                // Si el usuario existe, comprobar la contraseña
                if (usuario.getPassword().equals(password)) {
                    // Contraseña correcta, redirigir a página de inicio o dashboard
                    response.sendRedirect("Proyectos.jsp");
                } else {
                    // Contraseña incorrecta
                    request.setAttribute("errorMessage", "La contraseña es incorrecta.");
                    request.getRequestDispatcher("/JSP/LoginRegistro.jsp").forward(request, response);
                }
            } else {
                // El usuario no existe
                request.setAttribute("errorMessage", "El usuario no existe.");
                request.getRequestDispatcher("/JSP/LoginRegistro.jsp").forward(request, response);
            }
        } else if ("register".equals(action)) {
            // Validar registro
            UsuarioService usuarioService = new UsuarioServiceImpl();
            boolean usuarioExistente = usuarioService.validarUsuario(username);

            if (usuarioExistente) {
                // Si el usuario ya existe
                request.setAttribute("errorMessage", "El usuario ya existe.");
                request.getRequestDispatcher("/JSP/LoginRegistro.jsp").forward(request, response);
            } else {
                // Registrar nuevo usuario
                String confirmPassword = request.getParameter("confirmPassword");

                if (password.equals(confirmPassword)) {
                    // Registrar usuario
                    Usuarios nuevoUsuario = new Usuarios(username, password);
                    usuarioService.registrarUsuario(nuevoUsuario);

                    // Redirigir a la página de login con mensaje de éxito
                    request.setAttribute("successMessage", "Usuario registrado correctamente. Puedes iniciar sesión.");
                    request.getRequestDispatcher("/JSP/LoginRegistro.jsp").forward(request, response);
                } else {
                    // Las contraseñas no coinciden
                    request.setAttribute("errorMessage", "Las contraseñas no coinciden.");
                    request.getRequestDispatcher("/JSP/LoginRegistro.jsp").forward(request, response);
                }
            }
        }
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
