<%-- 
    Document   : Proyectos
    Created on : 26 feb 2025, 21:51:05
    Author     : patriciaaguayo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    String usuario = (String) session.getAttribute("Usuario");
    String tipoUsuario = (String) session.getAttribute("Tipo_Usuario");

    if (usuario == null) {
        response.sendRedirect("LoginRegistro.jsp"); // Si no hay sesión, redirigir a login
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Proyectos</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #121212;
                color: white;
            }
            .container {
                width: 90%;
                max-width: 1000px;
                margin: auto;
                padding: 20px;
                background-color: #1e1e1e;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(255, 255, 255, 0.2);
            }
            .section {
                background-color: #222;
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                cursor: pointer;
            }
            .section-content {
                display: none;
                padding: 10px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }
            th, td {
                border: 1px solid #444;
                padding: 10px;
                text-align: center;
            }
            th {
                background-color: #333;
            }
            .btn {
                background-color: #444;
                color: white;
                border: none;
                padding: 10px;
                cursor: pointer;
                border-radius: 5px;
            }
            .btn:hover {
                background-color: #555;
            }
            @media (max-width: 768px) {
                table, th, td {
                    display: block;
                    width: 100%;
                }
            }
        </style>
        <script>
            function toggleSection(id) {
                var section = document.getElementById(id);
                section.style.display = (section.style.display === 'block') ? 'none' : 'block';
            }

            function filtrarProyectos() {
                var estado = document.getElementById("estadoFiltro").value;
                window.location.href = "ProyectoServlet?estado=" + estado; // Enviar el estado para filtrar
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h1>Proyectos</h1>
            <label>Estado:</label>
            <select id="estadoFiltro" onchange="filtrarProyectos()">
                <option value="">Todos</option>
                <option value="Pendiente">Pendiente</option>
                <option value="En progreso">En progreso</option>
                <option value="Completado">Completado</option>
            </select>

            <div class="section" onclick="toggleSection('addSection')">Añadir ▼</div>
            <div class="section-content" id="addSection">
                <form action="/ProyectosJPS/ProyectoServlet" method="POST">
                    <label>Nombre:</label> <input type="text" name="nombreProyecto" required>
                    <label>Descripción:</label> <input type="text" name="descripcionProyecto" required>
                    <label>Finalización:</label> <input type="date" name="fechaFin" required>
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
            
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Estado</th>
                    <th>Inicio</th>
                    <th>Finalización</th>
                </tr>
                <c:forEach var="proyecto" items="${proyectos}">
                    <tr>
                        <td>${proyecto.Id_Proyecto}</td>
                        <td><a href="Tareas.jsp?id=${proyecto.Id_Proyecto}" style="color: white;">${proyecto.Nombre_Proyecto}</a></td>
                        <td>${proyecto.Descripcion_Proyecto}</td>
                        <td>${proyecto.Estado_Proyecto}</td> <!-- Aquí estaba el error -->
                        <td>${proyecto.Fecha_Inicio_Proyecto}</td>
                        <td>${proyecto.Fecha_Fin_Proyecto}</td>
                    </tr>
                </c:forEach>
            </table>
            
        <form action="/ProyectosJPS/CerrarSesionServlet" method="post">
            <button type="submit" class="btn">Cerrar Sesión</button>
        </form>
         
        </div>
    </body>
</html>









