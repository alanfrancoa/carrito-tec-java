<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ingresar Saldo</title>
    <!-- Incluir Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Ingresar Saldo</h1>
    
    <!-- Formulario de ingreso de saldo -->
    <form action="${pageContext.request.contextPath}/cliente" method="post">
        <input type="hidden" name="accion" value="IngresarSaldo" />
        
        <div class="form-group">
            <label for="saldo">Monto a ingresar:</label>
            <input type="number" name="saldo" id="saldo" class="form-control" required min="0" step="0.01" />
        </div>
        
        <button type="submit" class="btn btn-primary btn-block">Ingresar</button>
    </form>
    
    <!-- Enlace para volver al dashboard -->
    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/cliente?action=Dashboard" class="btn btn-secondary btn-block">Volver al Dashboard</a>

    </div>
</div>

<!-- Incluir JavaScript de Bootstrap (opcional para funcionalidades dinámicas) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
