<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrito</title>
</head>
<body>
	<h1>Articulo</h1>
	<p> Codigo: <c:out value="${articulo.codigo_art}"/> </p>
	<p> Nombre: <c:out value="${articulo.nombre }"/> </p>
	<p> Precio: <c:out value="${articulo.precio }"/> </p>
	<p> Stock: <c:out value="${articulo.stock }"/> </p>
	<p> Rubro: <c:out value="${articulo.rubro }"/> </p>
	</body>
</html>