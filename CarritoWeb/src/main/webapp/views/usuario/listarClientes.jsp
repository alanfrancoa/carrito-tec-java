<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Clientes</title>
    <!-- Incluir Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Lista de Clientes</h1>

    <!-- Tabla de clientes -->
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Nombre de Usuario</th>
                <th>Clave de Usuario</th>
                <th>Saldo</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.nombreUsuario}</td>
                    <td>${cliente.claveUsuario}</td>
                    <td>${cliente.saldo}</td>
                    <td>
                        <!-- Botón para eliminar un cliente -->
                        <form action="EmpleadoController" method="post" class="d-inline">
                            <input type="hidden" name="accion" value="eliminarCliente"/>
                            <input type="hidden" name="nombreCliente" value="${cliente.nombreUsuario}"/>
                            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Formulario para agregar un cliente -->
    <h3 class="mt-4">Agregar Cliente</h3>
    <form action="EmpleadoController" method="post">
        <input type="hidden" name="accion" value="crearCliente"/>
        
        <div class="form-group">
            <input type="text" name="nombre" class="form-control" placeholder="Nombre de Usuario" required />
        </div>
        
        <div class="form-group">
            <input type="password" name="pass" class="form-control" placeholder="Clave de Usuario" required />
        </div>
        
        <div class="form-group">
            <input type="number" name="saldo" class="form-control" placeholder="Saldo Inicial" step="0.01" required />
        </div>
        
        <button type="submit" class="btn btn-primary btn-block">Agregar Cliente</button>
    </form>
</div>

<!-- Incluir JavaScript de Bootstrap (opcional para funcionalidades dinámicas) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
