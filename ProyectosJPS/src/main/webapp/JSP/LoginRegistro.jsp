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
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

    <h2>Login o Registro</h2>

    <!-- Muestra un mensaje si hubo un error en el login o el registro -->
    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>

    <!-- Formulario de Login -->
    <h3>Iniciar sesión</h3>
    <form action="LoginRegistroServlet" method="post">
        <input type="text" name="nombreUsuario" placeholder="Nombre de usuario" required><br>
        <input type="password" name="password" placeholder="Contraseña" required><br>
        <button type="submit" name="action" value="login">Iniciar sesión</button>
    </form>

    <hr>

    <!-- Formulario de Registro -->
    <h3>¿No tienes cuenta? Regístrate</h3>
    <form action="LoginRegistroServlet" method="post">
        <input type="text" name="nombreUsuario" placeholder="Nombre de usuario" required><br>
        <input type="password" name="password" placeholder="Contraseña" required><br>
        <input type="password" name="confirmPassword" placeholder="Confirmar contraseña" required><br>
        <button type="submit" name="action" value="register">Registrarse</button>
    </form>

</body>
</html>
