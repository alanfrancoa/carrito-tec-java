<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Empleados</title>
</head>
<body>
<h1>Lista de Empleados</h1>
<table border="1">
    <tr>
        <th>Nombre de Usuario</th>
        <th>Clave de Usuario</th>
        <th>Acción</th>
    </tr>
    <c:forEach var="empleado" items="${empleados}">
        <tr>
            <td>${empleado.nombreUsuario}</td>
            <td>${empleado.claveUsuario}</td>
            <td>
                <!-- Botón para eliminar un empleado -->
                <form action="EmpleadoController" method="post">
                    <input type="hidden" name="accion" value="eliminarUsuario"/>
                    <input type="hidden" name="id" value="${empleado.nombreUsuario}"/>
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Formulario para agregar un empleado -->
<form action="EmpleadoController" method="post">
    <input type="hidden" name="accion" value="crearUsuario"/>
    <input type="text" name="nombre" placeholder="Nombre de Usuario" required/>
    <input type="password" name="pass" placeholder="Clave de Usuario" required/>
    <input type="hidden" name="tipo" value="EMPLEADO"/>
    <button type="submit">Agregar Empleado</button>
</form>
</body>
</html>