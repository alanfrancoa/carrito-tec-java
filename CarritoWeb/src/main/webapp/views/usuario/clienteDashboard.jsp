<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard - Cliente</title>
<!-- Incluir Bootstrap -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://unpkg.com/feather-icons"></script>
</head>
<body>

	<div class="container mt-5">
		<h1 class="mb-4">
			Bienvenido,
			<c:out value="${sessionScope.usuario.getNombreUsuario()}" />
		</h1>
		<h3>
			Saldo: $
			<c:out value="${sessionScope.usuario.getSaldo()}" />
		</h3>

		<!-- Mensaje Condicional -->
		<c:if test="${not empty sessionScope.mensaje}">
			<div class="alert alert-info" role="alert">
				${sessionScope.mensaje}</div>
			<c:remove var="mensaje" scope="session" />
		</c:if>


		<h3 class="mb-3">Opciones:</h3>
		<ul class="list-group">
			<li class="list-group-item"><a
				href="${pageContext.request.contextPath}/cliente?accion=Comprar"
				class="btn btn-success btn-block"><i data-feather="shopping-cart"></i>
					COMPRAR</a></li>
			<li class="list-group-item"><a
				href="${pageContext.request.contextPath}/cliente?accion=IngresarSaldo"
				class="btn btn-primary btn-block">Ingresar saldo</a></li>
			<li class="list-group-item"><a
				href="${pageContext.request.contextPath}/cliente?accion=RetirarSaldo"
				class="btn btn-warning btn-block">Retirar saldo</a></li>
			<li class="list-group-item"><a
				href="${pageContext.request.contextPath}/cliente?accion=TransferirSaldo"
				class="btn btn-info btn-block">Transferir saldo</a></li>
			<li class="list-group-item"><a
				href="${pageContext.request.contextPath}/Login?accion=Logout"
				class="btn btn-danger btn-block">Cerrar Sesión</a></li>
		</ul>
	</div>

	<!-- Incluir JavaScript de Bootstrap (opcional para funcionalidades dinámicas) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
		feather.replace();
	</script>

</body>
</html>
