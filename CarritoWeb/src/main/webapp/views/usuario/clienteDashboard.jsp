<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Cliente</title>
    <!-- Incluir Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Bienvenido, <c:out value="${sessionScope.cliente.getNombreUsuario()}"/></h1>
    <h3>Saldo: $<c:out value="${sessionScope.cliente.getSaldo()}"/></h3>

    <!-- Mensaje Condicional -->
    <c:if test="${not empty mensaje}">
        <div class="alert alert-info" role="alert">
            ${mensaje}
        </div>
    </c:if>

    <h3 class="mb-3">Opciones:</h3>
    <ul class="list-group">
        <li class="list-group-item">
            <a href="${pageContext.request.contextPath}/cliente?action=IngresarSaldo" class="btn btn-primary btn-block">Ingresar saldo</a>

        </li>
        <li class="list-group-item">
            <a href="${pageContext.request.contextPath}/cliente?action=RetirarSaldo" class="btn btn-warning btn-block">Retirar saldo</a>
        </li>
        <li class="list-group-item">
            <a href="${pageContext.request.contextPath}/client?action=TransferirSaldo" class="btn btn-success btn-block">Transferir saldo</a>
        </li>
    </ul>
</div>

<!-- Incluir JavaScript de Bootstrap (opcional para funcionalidades dinámicas) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
