<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Incluir Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Contenedor principal para el formulario -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">
            <h1 class="mb-4 text-center">¡Bienvenido!</h1>
            
            <!-- Formulario de login -->
            <form action="Login" method="post" class="border p-4 rounded shadow-sm">
                <input type="hidden" value="Auth" name="accion">

                <!-- Campo de usuario -->
                <div class="mb-3">
                    <label for="nombreUsuario" class="form-label">Usuario</label>
                    <input type="text" id="nombreUsuario" name="nombreUsuario" class="form-control" required />
                </div>

                <!-- Campo de clave -->
                <div class="mb-3">
                    <label for="claveUsuario" class="form-label">Clave</label>
                    <input type="password" id="claveUsuario" name="claveUsuario" class="form-control" required />
                </div>

                <!-- Botón de submit -->
                <div class="mb-3 text-center">
                    <button type="submit" class="btn btn-primary w-100">Ingresar</button>
                </div>
            </form>
            <c:if test="${param.error != null}">
				<div class="alert alert-danger mt-3">
					<strong>Error!</strong> ${param.error}
				</div>
			</c:if>
        </div>
    </div>
</div>

</body>
</html>
