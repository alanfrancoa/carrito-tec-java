<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Retirar Saldo</title>
</head>
<body>
    <h1>Retirar Saldo</h1>
    <form action="ClienteController" method="post">
        <input type="hidden" name="accion" value="RetirarSaldo" />
        <label for="saldo">Monto a retirar: </label>
        <input type="number" name="saldo" id="saldo" required min="0" step="0.01" />
        <button type="submit">Retirar</button>
    </form>
    <a href="ClienteController?action=Dashboard">Volver al dashboard</a>
</body>
</html>
