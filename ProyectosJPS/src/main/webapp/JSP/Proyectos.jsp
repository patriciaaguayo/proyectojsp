<%-- 
    Document   : Proyectos
    Created on : 26 feb 2025, 21:51:05
    Author     : patriciaaguayo
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    String usuario = (String) session.getAttribute("Usuario");
    String tipoUsuario = (String) session.getAttribute("Tipo_Usuario");

    if (usuario == null) {
        response.sendRedirect("CSS/LoginRegistro.jsp"); // Si no hay sesión, redirigir a login
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Proyectos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ProyectosStyle.css">

        <!-- Script para mostrar el mensaje emergente -->
        <script>
            <% 
                String mensaje = (String) request.getAttribute("mensaje");
                String errorMessage = (String) request.getAttribute("errorMessage");
            %>
                
            <% if (mensaje != null) { %>
                alert("<%= mensaje %>");
            <% } %>

            <% if (errorMessage != null) { %>
                alert("<%= errorMessage %>");
            <% } %>
                            
            function toggleSection(id) {
                var section = document.getElementById(id);
                section.style.display = (section.style.display === 'block') ? 'none' : 'block';
            }

            function filtrarProyectos() {
                var estado = document.getElementById("estadoFiltro").value;
                window.location.href = "ProyectoServlet?estado=" + estado; // Enviar el estado para filtrar
            }

            function buscarPorEstado() {
                var estado = document.getElementById("estadoFiltro").value;
                window.location.href = "ProyectoServlet?estado=" + estado; // Filtrar por estado al hacer clic en el botón
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h1>Proyectos</h1>

            <!-- Filtro de estado con un botón de búsqueda -->
            
            <form action="/ProyectosJPS/ProyectoServlet" method="GET" class="filter-container">
                <label>Estado:</label>
                <select id="estadoFiltro" name="estado">
                    <option value="">Todos</option>
                    <option value="En Curso">En curso</option>
                    <option value="Completado">Completado</option>
                </select>

                <!-- Botón de búsqueda -->
                <button onclick="buscarPorEstado()" class="btn">Buscar</button>
            
            </form>

            <div class="section" onclick="toggleSection('addSection')">Añadir ▼</div>
            <div class="section-content" id="addSection">
                <form action="/ProyectosJPS/ProyectoServlet" method="POST">
                    <label>Nombre:</label> <input class="Campo" type="text" name="nombreProyecto" required>
                    <label>Descripción:</label> <input class="Campo" type="text" name="descripcionProyecto" required>
                    <label>Finalización:</label> <input class="Campo" type="date" name="fechaFin" required>
                    <button type="submit" class="btn">Añadir</button>
                </form>
            </div>

            <% if ("Admin".equals(tipoUsuario)) { %>
                <div class="section" onclick="toggleSection('deleteSection')">Eliminar ▼</div>
                <div class="section-content" id="deleteSection">
                    <form action="/ProyectosJPS/ProyectoServlet" method="POST">
                        <label>ID:</label> <input type="number" name="idProyecto" required>
                        <button type="submit" class="btn">Eliminar</button>
                    </form>
                </div>
            <% } %>

            <!-- Tabla de proyectos -->
            <table>
                <c:if test="${not empty proyectos}">
                    <c:forEach var="proyecto" items="${proyectos}">
                        <tr>
                            <td>${proyecto.idProyecto}</td>
                            <td>
                                
                                <%
                                    System.out.println("Usuario: " + usuario);
                                    System.out.println("Tipo de Usuario: " + tipoUsuario);
                                %>
                                
                                <a class="NProyecto" href="/ProyectosJPS/JSP/Tareas.jsp?id=${proyecto.idProyecto}&nombre=${proyecto.nombreProyecto}&descripcion=${proyecto.descripcionProyecto}&estado=${proyecto.estadoProyecto}" style="color: white;">
                                    ${proyecto.nombreProyecto}
                                </a>

                            </td>
                            <td>${proyecto.descripcionProyecto}</td>
                            <td>${proyecto.estadoProyecto}</td>
                            <td>${proyecto.fechaInicioProyecto}</td>
                            <td>${proyecto.fechaFinProyecto}</td>
                        </tr>
                    </c:forEach>
                </c:if>

                <c:if test="${empty proyectos}">
                    <tr><td colspan="6">No hay proyectos disponibles</td></tr>
                </c:if>
            </table>

            <form action="${pageContext.request.contextPath}/CerrarSesionServlet" method="post" id="Cerrar">
                <button type="submit" class="btn">Cerrar Sesión</button>
            </form>
        </div>
    </body>
</html>











