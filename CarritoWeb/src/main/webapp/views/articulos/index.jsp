<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Articulos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

 <a href="articulos?accion=CrearArticulos" class="btn btn-primary" > Agregar Articulo </a> </button>
 
	<table class="table">
		<thead>
			<tr> 
			<th scope="col">Codigo</th> 
			<th scope="col">Nombre</th> 
			<th scope="col">Precio</th> 
			<th scope="col">Stock</th> 
			<th scope="col">Rubro</th> 
			<th scope="col">Acciones</th> 
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
			
			<td> <a href="articulos?accion=MostrarArticulos&codigo_art=${articulo.codigo_art}" class="btn btn-info"> Ver</td> </a>
			<td> <a href="articulos?accion=EditarArticulos&codigo_art=${articulo.codigo_art}" class="btn btn-warning"> Editar</td> </a>
						
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>

</html>