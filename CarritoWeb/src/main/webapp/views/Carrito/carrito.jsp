<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Carrito de Compras</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h1 class="mb-4">Tu Carrito de Compras</h1>
		
		<c:if test="${param.error != null}">
				<div class="alert alert-danger mt-3">
					<strong>Error!</strong> ${param.error}
				</div>
			</c:if>

		<!-- Mensaje de sesión -->
		<c:if test="${not empty sessionScope.mensaje}">
			<div class="alert alert-info alert-dismissible fade show"
				role="alert">
				${sessionScope.mensaje}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<!-- Verificar si el carrito está vacío -->
		<c:if test="${carrito.verCarrito().isEmpty()}">
			<div class="alert alert-warning" role="alert">El carrito está
				vacío. ¡Agrega productos para continuar!</div>
			<div class="d-flex justify-content-between mt-4">	
				<a href="cliente?accion=Dashboard" class="btn btn-secondary">Volver al Dashboard </a> 
				<a href="carrito?accion=agregar" class="btn btn-primary">Agregar Productos</a>
			</div>	
		</c:if>

		<!-- Tabla con los productos del carrito -->
		<c:if test="${not carrito.verCarrito().isEmpty()}">
			<table class="table table-striped table-bordered">
				<thead class="table-dark">
					<tr>
						<th>Producto</th>
						<th>Precio Unitario</th>
						<th>Cantidad</th>
						<th>Accion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${carrito.verCarrito()}">
						<tr>
							<td>${item.producto.nombre}</td>
							<td>$${item.producto.precio}</td>
							<td>${item.cantidad}</td>
							<td>
								<!-- Btn eliminar renglon del carrito -->
								<form method="get"
									action="${pageContext.request.contextPath}/carrito">
									<input type="hidden" name="accion" value="eliminarRenglon">
									<input type="hidden" name="codigo_art"
										value="${item.producto.codigo_art}">
									<button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- Total fuera de la tabla y en grande -->
			<div class="text-center mt-4">
				<p class="h3 font-weight-bold">Total:
					$${carrito.verMontoTotal()}</p>
			</div>

			<!-- Botones para acciones -->
			<div class="d-flex justify-content-between mt-4">
				<a href="cliente?accion=Dashboard" class="btn btn-secondary">Volver al Dashboard </a> 
				<a href="carrito?accion=agregar" class="btn btn-primary"> Agregar más Productos </a> 
				<a href="carrito?accion=finalizar" class="btn btn-success"> Finalizar compra </a>
			</div>
		</c:if>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>





