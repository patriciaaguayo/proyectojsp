<%-- 
    Document   : Proyectos
    Created on : 26 feb 2025, 21:51:05
    Author     : patriciaaguayo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                background-color: #f4f4f4;
            }

            .header {
                text-align: center;
                padding: 20px;
                background-color: #333;
                color: white;
            }

            .projects-container {
                padding: 20px;
                background-color: #fff;
                margin: 0 20px;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .project {
                margin: 10px 0;
                background-color: #f9f9f9;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ddd;
            }

            .no-projects {
                text-align: center;
                color: red;
                font-size: 18px;
                padding: 20px;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h1>Proyectos</h1>
        </div>

        <div class="projects-container">
            <c:choose>
                <c:when test="${not empty proyectos}">
                    <c:forEach var="proyecto" items="${proyectos}">
                        <div class="project">
                            <h3>${proyecto.nombreProyecto}</h3>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="no-projects">
                        <p>Oops, no se encuentra ningún Proyecto.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>


