<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Articulos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Botón Agregar Artículo con margen superior y margen inferior -->
<div class="container mt-4">
    <a href="articulos?accion=CrearArticulos" class="btn btn-primary mb-4">Agregar Artículo</a>
</div>

<!-- Contenedor centrado de la tabla -->
<div class="container">
    <table class="table table-bordered mx-auto">
        <thead>
            <tr>
                <th scope="col">Código</th>
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
                    <td><c:out value="${articulo.codigo_art}" /></td>
                    <td><c:out value="${articulo.nombre}" /></td>
                    <td><c:out value="${articulo.precio}" /></td>
                    <td><c:out value="${articulo.stock}" /></td>
                    <td><c:out value="${articulo.rubro}" /></td>
                    <td>
                        <a href="articulos?accion=MostrarArticulos&codigo_art=${articulo.codigo_art}" class="btn btn-info me-2">Ver</a>
                        <a href="articulos?accion=EditarArticulos&codigo_art=${articulo.codigo_art}" class="btn btn-warning">Editar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
