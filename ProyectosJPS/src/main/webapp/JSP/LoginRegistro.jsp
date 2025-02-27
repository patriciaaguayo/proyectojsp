<%-- 
    Document   : LoginRegistro
    Created on : 26 feb 2025, 19:53:14
    Author     : patriciaaguayo
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login o Registro</title>
    <!-- Referencia a la hoja de estilos externa -->
    <link rel="stylesheet" href="CSS/LoginStyle.css">

    <script type="text/javascript">
        // Mostrar mensaje de error o éxito como alerta emergente al cargar la página
        window.onload = function() {
            var errorMessage = "<c:out value='${errorMessage}' />";
            var successMessage = "<c:out value='${successMessage}' />";

            // Mostrar mensaje de error si existe
            if (errorMessage) {
                alert(errorMessage);
            }

            // Mostrar mensaje de éxito si existe
            if (successMessage) {
                alert(successMessage);
            }
        };
    </script>
</head>
<body>

    <div class="container">
        <h2>Iniciar sesión o Registro</h2>

        <!-- Formulario de Login -->
        <h3>Iniciar sesión</h3>
        <form action="LoginRegistroServlet" method="post">
            <input type="text" name="nombreUsuario" placeholder="Nombre de usuario" required><br>
            <input type="password" name="password" placeholder="Contraseña" required><br>
            <button type="submit" name="action" value="login" id="BotonIniciar">Iniciar sesión</button>
        </form>

        <hr>

        <!-- Formulario de Registro -->
        <h3>¿No tienes cuenta? Regístrate</h3>
        <form action="LoginRegistroServlet" method="post">
            <input type="text" name="nombreUsuario" placeholder="Nombre de usuario" required><br>
            <input type="password" name="password" placeholder="Contraseña" required><br>
            <button type="submit" name="action" value="register">Registrarse</button>
        </form>
    </div>

</body>
</html>

