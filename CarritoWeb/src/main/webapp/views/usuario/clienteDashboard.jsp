<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Cliente</title>
</head>
<body>
    <h1>Bienvenido, ${cliente.nombreUsuario}</h1>
    <h2>Saldo actual: $ ${cliente.saldo}</h2>

    <c:if test="${not empty mensaje}">
        <p>${mensaje}</p>
    </c:if>

    <h3>Opciones:</h3>
    <ul>
        <li><a href="ClienteController?action=IngresarSaldo">Ingresar saldo</a></li>
        <li><a href="ClienteController?action=RetirarSaldo">Retirar saldo</a></li>
        <li><a href="ClienteController?action=TransferirSaldo">Transferir saldo</a></li>
    </ul>
</body>
</html>
