<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar al Carrito</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<!-- Contenedor centrado de la tabla -->
<div class="container">
    <h1>Lista de Artículos</h1>
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
                        <!-- Formulario para agregar producto al carrito -->
                        <form action="carrito" method="post" class="d-flex align-items-center">
                            <!-- Input cantidad -->
                            <input 
                                type="number" 
                                name="cantidad" 
                                min="1" 
                                max="${articulo.stock}" 
                                class="form-control me-2" 
                                placeholder="Cantidad" 
                                required />
                            <!-- Campos ocultos -->
                            <input type="hidden" name="accion" value="agregarAlCarrito" />
                            <input type="hidden" name="codigo_art" value="${articulo.codigo_art}" />
                            <input type="hidden" name="nombre" value="${articulo.nombre}" />
                            <input type="hidden" name="precio" value="${articulo.precio}" />
                            <input type="hidden" name="rubro" value="${articulo.rubro}" />
                            <input type="hidden" name="stock" value="${articulo.stock}" />
                            <!-- Botón agregar -->
                            <button type="submit" class="btn btn-success">Agregar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-between mt-4">
				<a href="cliente?accion=Dashboard" class="btn btn-secondary">Volver al dashboard
				</a>
				<a href="carrito?accion=index" class="btn btn-primary">
					Ver carrito
				</a>
				<a href="carrito?accion=finalizar" class="btn btn-success">
					Finalizar compra
				</a>
			</div>
</div>

<!-- Mostrar mensajes de error de la sesión -->
<c:if test="${not empty sessionScope.mensajeError}">
    <div class="alert alert-danger mt-3">
        ${sessionScope.mensajeError}
    </div>
</c:if>

</body>
</html>