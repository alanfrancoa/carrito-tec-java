<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transferir Saldo</title>
    <!-- Incluir Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Contenedor principal -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">
            <h1 class="mb-4 text-center">Transferir Saldo</h1>

            <!-- Formulario para transferir saldo -->
            <form action="${pageContext.request.contextPath}/cliente" method="post" class="border p-4 rounded shadow-sm">
                <input type="hidden" name="accion" value="TransferirSaldo" />

                <!-- Campo de usuario destinatario -->
                <div class="mb-3">
                    <label for="usuarioDestino" class="form-label">Nombre de usuario del destinatario:</label>
                    <input type="text" name="usuarioDestino" id="usuarioDestino" class="form-control" required />
                </div>

                <!-- Campo de monto a transferir -->
                <div class="mb-3">
                    <label for="monto" class="form-label">Monto a transferir:</label>
                    <input type="number" name="monto" id="monto" class="form-control" required min="0" step="0.01" />
                </div>

                <!-- Botón de submit -->
                <div class="mb-3 text-center">
                    <button type="submit" class="btn btn-success w-100">Transferir</button>
                </div>
            </form>

            <!-- Enlace para volver al dashboard -->
            <div class="mt-3 text-center">
                <a href="cliente?accion=Dashboard" class="btn btn-secondary w-100">Volver al Dashboard</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
