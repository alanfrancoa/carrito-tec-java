<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Historial Completo de Compras</title>
</head>
<body>
    <h1>Historial de Todas las Compras</h1>
    <table border="1">
        <thead>
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
</body>
</html>