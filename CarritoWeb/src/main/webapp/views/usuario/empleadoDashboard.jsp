<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Empleado</title>
</head>
<body>
    <h1>Bienvenido, Empleado</h1>
    <h3>Opciones:</h3>
    <ul>
        <li><a href="EmpleadosController?accion=ListarArticulos">Gestionar Artículos</a></li>
        <li><a href="EmpleadosController?accion=ListarClientes">Gestionar Clientes</a></li>
        <li><a href="EmpleadosController?accion=ListarEmpleados">Gestionar Empleados</a></li>
        <li><a href="EmpleadosController?accion=HistorialCompras">Ver Historial de Compras</a></li>
    </ul>
</body>
</html>