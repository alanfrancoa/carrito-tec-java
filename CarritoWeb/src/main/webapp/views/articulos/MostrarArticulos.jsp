<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mostrar Artículo</title>
    <!-- Incluir Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Detalles del Artículo</h1>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title"></h5>

            <p><strong>Código:</strong> <c:out value="${articulo.codigo_art}"/></p>
            <p><strong>Nombre:</strong> <c:out value="${articulo.nombre}"/></p>
            <p><strong>Precio:</strong> <c:out value="${articulo.precio}"/></p>
            <p><strong>Stock:</strong> <c:out value="${articulo.stock}"/></p>
            <p><strong>Rubro:</strong> <c:out value="${articulo.rubro}"/></p>

            <form action="articulos" method="post">
                <input type="hidden" name="codigo" value="${articulo.codigo_art}">
                <input type="hidden" name="accion" value="EliminarArticulos">
                <div class="d-flex gap-3" >  
	                <button type="submit" class="btn btn-danger w-25">Eliminar Artículo</button>
		        	<a href="empleado?accion=listarArticulos" class="btn btn-secondary btn-block w-25 ml-2">Cancelar</a>   
        		</div>
            </form>
        </div>
    </div>
</div>

<!-- Incluir JavaScript de Bootstrap (opcional para funcionalidades dinámicas) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
