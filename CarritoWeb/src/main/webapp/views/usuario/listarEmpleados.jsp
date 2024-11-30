<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Empleados</title>
    <!-- Incluir Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Lista de Empleados</h1>

    <!-- Tabla de empleados -->
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Nombre de Usuario</th>
                <th>Clave de Usuario</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="empleado" items="${empleados}">
                <tr>
                    <td>${empleado.nombreUsuario}</td>
                    <td>${empleado.claveUsuario}</td>
                    <td>
                        <!-- Botón para eliminar un empleado -->
                        <form action="EmpleadoController" method="post" class="d-inline">
                            <input type="hidden" name="accion" value="eliminarUsuario"/>
                            <input type="hidden" name="id" value="${empleado.nombreUsuario}"/>
                            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Formulario para agregar un empleado -->
    <h3 class="mt-4">Agregar Empleado</h3>
    <form action="EmpleadoController" method="post">
        <input type="hidden" name="accion" value="crearUsuario"/>
        <input type="hidden" name="tipo" value="EMPLEADO"/>
        
        <div class="form-group">
            <input type="text" name="nombre" class="form-control" placeholder="Nombre de Usuario" required />
        </div>
        
        <div class="form-group">
            <input type="password" name="pass" class="form-control" placeholder="Clave de Usuario" required />
        </div>

        <button type="submit" class="btn btn-primary btn-block">Agregar Empleado</button>
    </form>
</div>

<!-- Incluir JavaScript de Bootstrap (opcional para funcionalidades dinámicas) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
