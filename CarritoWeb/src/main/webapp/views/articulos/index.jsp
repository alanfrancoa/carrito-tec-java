<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Articulos</title>
</head>
<body>

 <a href="articulos?accion=CrearArticulos"> Agregar Articulo </a>
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
			
			<td> <a href="articulos?accion=EditarArticulos&codigo_art=${articulo.codigo_art}"> Editar</td> </a>
			<td> <a href="#"> Eliminar</td> </a>
			
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>

</html>