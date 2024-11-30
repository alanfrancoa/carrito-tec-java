<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Carrito</title>
	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
	<h1 class="mb-4">Articulos de carrito</h1>
	<div class="mb-4">
	<%-- acá va el link para volver a la pantalla de agregar articulos --%>
		<a href="#" >Agregar mas articulos</a> 
	</div>	
		<table class="table table-striped mb-4">
			<thead>
				<tr class="table-info">
				<th>Articulo</th>
				<th>Cantidad</th>
				</tr>
			</thead>
			<tbody>
			<%-- acá falta pasar la lista que almacena el carrito --%>
				<c:forEach var="articulo" items="${}"> 
				<tr>
				<td> <c:out value="${articulo.nombre }"/> </td>
				<td> <c:out value="${articulo.cantidad }"/> </td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
		<%-- acá va el link para ir a la pantalla de pago y factura --%>
			<a href="#">Ir a pagar</a>
		</div>
	</div>
	</body>
</html>