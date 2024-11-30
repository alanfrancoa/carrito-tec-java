<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empleado</title>
    <!-- Incluir Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Bienvenido, Empleado</h1>

    <h3 class="mb-4">Opciones:</h3>
    
    <ul class="list-group">
        <li class="list-group-item">
            <a href="EmpleadosController?accion=ListarArticulos" class="btn btn-primary btn-block">Gestionar Artículos</a>
        </li>
        <li class="list-group-item">
            <a href="EmpleadosController?accion=ListarClientes" class="btn btn-secondary btn-block">Gestionar Clientes</a>
        </li>
        <li class="list-group-item">
            <a href="EmpleadosController?accion=ListarEmpleados" class="btn btn-warning btn-block">Gestionar Empleados</a>
        </li>
        <li class="list-group-item">
            <a href="EmpleadosController?accion=HistorialCompras" class="btn btn-info btn-block">Ver Historial de Compras</a>
        </li>
    </ul>
</div>

<!-- Incluir JavaScript de Bootstrap (opcional para funcionalidades dinámicas) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
