<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Articulos</title>
</head>
<body>

	<table border="1">
		<thead>
			<tr> 
			<th>Codigo</th> 
			<th>Nombre</th> 
			<th>Precio</th> 
			<th>Stock</th> 
			<th>Rubro</th> 
			</tr>
		</thead>
	<tbody>
		
		<c:forEach var="articulo" items="${listita}">
			<tr> 
			<td> <c:out value="${articulo.codigo_art}" /> </td>
			<td> <c:out value="${articulo.nombre}" /> </td>
			<td> <c:out value="${articulo.precio}" /> </td>
			<td> <c:out value="${articulo.stock}" /> </td>
			<td> <c:out value="${articulo.rubro}" /> </td>
			
			<td> <a href="#"> Cargar</td>
			<td> <a href="#"> Editar</td>
			<td> <a href="#"> Eliminar</td>
			
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>

</html>