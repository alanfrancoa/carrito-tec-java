<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Historial de Compras - ${nombreCliente}</title>
</head>
<body>
    <h1>Historial de Compras de ${nombreCliente}</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Número de Factura</th>
                <th>Fecha</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="compra" items="${comprasCliente}">
                <tr>
                    <td>${compra.numeroFactura}</td>
                    <td>${compra.fechaCompra}</td>
                    <td>${compra.montoTotal}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>