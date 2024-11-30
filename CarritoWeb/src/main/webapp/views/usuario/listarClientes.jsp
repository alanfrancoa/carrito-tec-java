<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Clientes</title>
</head>
<body>
<h1>Lista de Clientes</h1>
<table border="1">
    <tr>
        <th>Nombre de Usuario</th>
        <th>Clave de Usuario</th>
        <th>Saldo</th>
        <th>Acción</th>
    </tr>
    <c:forEach var="cliente" items="${clientes}">
        <tr>
            <td>${cliente.nombreUsuario}</td>
            <td>${cliente.claveUsuario}</td>
            <td>${cliente.saldo}</td>
            <td>
                <!-- Botón para eliminar un cliente -->
                <form action="EmpleadoController" method="post">
                    <input type="hidden" name="accion" value="eliminarCliente"/>
                    <input type="hidden" name="nombreCliente" value="${cliente.nombreUsuario}"/>
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Formulario para agregar un cliente -->
<form action="EmpleadoController" method="post">
    <input type="hidden" name="accion" value="crearCliente"/>
    <input type="text" name="nombre" placeholder="Nombre de Usuario" required/>
    <input type="password" name="pass" placeholder="Clave de Usuario" required/>
    <input type="number" name="saldo" placeholder="Saldo Inicial" step="0.01" required/>
    <button type="submit">Agregar Cliente</button>
</form>
</body>
</html>
