<%-- 
    Document   : Tareas
    Created on : 1 mar 2025, 15:12:56
    Author     : patriciaaguayo
--%>

<%@page import="Util.HibernateUtil"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="Service.TareaServiceImpl"%>
<%@page import="entities.Tareas"%>
<%@page import="Service.TareaService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
    // Obtener los parámetros de la URL
    String idProyectoStr = request.getParameter("id");
    String nombreProyecto = request.getParameter("nombre");
    String descripcionProyecto = request.getParameter("descripcion");
    String estadoProyecto = request.getParameter("estado");

    // Lista para almacenar las tareas
    List<Tareas> tareas = new ArrayList<>();

    // Verificar si el idProyecto es válido
    if (idProyectoStr != null && !idProyectoStr.trim().isEmpty()) {
        try {
            int idProyecto = Integer.parseInt(idProyectoStr.trim());

            // Obtener la SessionFactory desde HibernateUtil
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            
            // Obtener las tareas del proyecto desde el servicio
            TareaService tareaService = new TareaServiceImpl(sessionFactory); // Pasamos la SessionFactory
            tareas = tareaService.obtenerTareasPorProyecto(idProyecto);

            // DEBUG: Verificar en consola si se obtienen tareas
            System.out.println("Proyecto ID: " + idProyecto);
            System.out.println("Nombre Proyecto: " + nombreProyecto);
            System.out.println("Descripción Proyecto: " + descripcionProyecto);
            System.out.println("Estado Proyecto: " + estadoProyecto);
            System.out.println("Tareas encontradas: " + tareas.size());

        } catch (NumberFormatException e) {
            System.out.println("Error: ID del proyecto no es un número válido.");
            e.printStackTrace();
        }
    } else {
        System.out.println("Error: ID del proyecto no recibido en la URL.");
    }

    // Enviar atributos al request para JSTL
    request.setAttribute("nombreProyecto", nombreProyecto);
    request.setAttribute("descripcionProyecto", descripcionProyecto);
    request.setAttribute("estadoProyecto", estadoProyecto);
    request.setAttribute("tareas", tareas);
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tareas del Proyecto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ProyectosStyle.css">
</head>
<body>

    <div class="container">
        <h1>Tareas del Proyecto: ${nombreProyecto}</h1>
        <p><strong>Descripción:</strong> ${descripcionProyecto}</p>
        <p><strong>Estado:</strong> ${estadoProyecto}</p>
        
        <!-- Depuración: Mostrar ID del Proyecto y cantidad de tareas encontradas -->
        <p>ID Proyecto: <%= idProyectoStr %></p>
        <p>Cantidad de tareas encontradas: ${tareas.size()}</p>
        
        <div class="section" onclick="toggleSection('addSection')">Añadir ▼</div>
            <div class="section-content" id="addSection">
                <form action="/ProyectosJPS/TareaServlet" method="POST">
                    <div>
                        <label for="descripcionTarea">Descripción:</label>
                        <input type="text" id="descripcionTarea" name="descripcionTarea" required>
                    </div>
                    <div>
                        <label for="responsable">Responsable:</label>
                        <input type="text" id="responsable" name="responsable" required>
                    </div>
                    <div>
                        <label for="fechaFinTarea">Fecha de Fin:</label>
                        <input type="date" id="fechaFinTarea" name="fechaFinTarea" required>
                    </div>
                    <input type="hidden" name="idProyecto" value="${idProyecto}">
                    <button type="submit" class="btn">Añadir Tarea</button>
                </form>
            </div>
        
        <%
            String usuario = (String) session.getAttribute("Usuario");
            String tipoUsuario = (String) session.getAttribute("Tipo_Usuario");
            
            System.out.println("Usuario: " + usuario);
            System.out.println("Tipo de Usuario: " + tipoUsuario);

            if (usuario == null) {
                response.sendRedirect("CSS/LoginRegistro.jsp"); // Redirigir al login si no hay sesión
                return;
            }
            
            int idProyecto = Integer.parseInt(idProyectoStr.trim());
        %>

        <!-- Mostrar el botón de eliminar tarea solo si el usuario es Admin -->
        <% if ("Admin".equals(tipoUsuario)) { %>
            <div class="section" onclick="toggleSection('deleteTareaSection')">Eliminar Tarea ▼</div>
            <div class="section-content" id="deleteTareaSection">
                <form action="/ProyectosJPS/TareaServlet" method="POST">
                    <input type="hidden" name="accion" value="eliminar">
                    <label>ID de Tarea a Eliminar:</label> 
                    <input type="number" name="idTarea" required>
                    <input type="hidden" name="idProyecto" value="<%= idProyecto %>">
                    <button type="submit" class="btn">Eliminar Tarea</button>
                </form>
            </div>
        <% } %>

        <!-- Tabla de tareas -->
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Descripción</th>
                    <th>Responsable</th>
                    <th>Estado</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty tareas}">
                    <c:forEach var="tarea" items="${tareas}">
                        <tr>
                            <td>${tarea.idTarea}</td>
                            <td>${tarea.descripcionTarea}</td>
                            <td>${tarea.responsable}</td>
                            <td>${tarea.estadoTarea}</td>
                            <td>${tarea.fechaInicioTarea}</td>
                            <td>${tarea.fechaFinTarea}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty tareas}">
                    <tr><td colspan="6">No hay tareas disponibles para este proyecto.</td></tr>
                </c:if>
            </tbody>
        </table>

        <!-- Volver a la página anterior -->
        <a href="/ProyectosJPS/JSP/Proyectos.jsp">Volver a Proyectos</a>

    </div>

    <script>
        // Función para alternar la visibilidad del formulario
        function toggleSection(id) {
            var section = document.getElementById(id);
            section.style.display = (section.style.display === 'block') ? 'none' : 'block';
        }
    </script>

</body>
</html>


