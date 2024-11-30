<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial Completo de Compras</title>
    <!-- Incluir Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Historial de Todas las Compras</h1>

    <!-- Tabla de Compras Totales -->
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Cliente</th>
                <th>Número de Factura</th>
                <th>Fecha</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="compra" items="${todasLasCompras}">
                <tr>
                    <td>${compra.nombreCliente}</td>
                    <td>${compra.numeroFactura}</td>
                    <td>${compra.fechaCompra}</td>
                    <td>${compra.montoTotal}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Incluir JavaScript de Bootstrap (opcional para funcionalidades dinámicas) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
