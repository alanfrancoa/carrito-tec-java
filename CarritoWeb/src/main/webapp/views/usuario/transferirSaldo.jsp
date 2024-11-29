<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Transferir Saldo</title>
</head>
<body>
    <h1>Transferir Saldo</h1>
    <form action="ClienteController" method="post">
        <input type="hidden" name="accion" value="TransferirSaldo" />
        <label for="usuarioDestino">Nombre de usuario del destinatario: </label>
        <input type="text" name="usuarioDestino" id="usuarioDestino" required />
        <br />
        <label for="monto">Monto a transferir: </label>
        <input type="number" name="monto" id="monto" required min="0" step="0.01" />
        <button type="submit">Transferir</button>
    </form>
    <a href="ClienteController?action=Dashboard">Volver al dashboard</a>
</body>
</html>
