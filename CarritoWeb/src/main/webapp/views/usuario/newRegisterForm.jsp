<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo usuario</title>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Registro de nuevo usuario</h1>

        <!-- Formulario de registro -->
        <form action="AltaUsuario" method="post" class="h4 text-left mb-4">
            <input type="hidden" value="create" name="accion">

            <div class="mb-3">
                <label for="nombreUsuario" class="form-label">Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" class="form-control" required />
            </div>

            <div class="mb-3">
                <label for="claveUsuario" class="form-label">Contraseña:</label>
                <input type="password" id="claveUsuario" name="claveUsuario" class="form-control" required />
            </div>

            <div class="mb-3">
                <label class="form-label">Tipo de usuario:</label><br>
                <input type="radio" id="cliente" name="tipoUsuario" value="CLIENTE" required />
                <label for="cliente">Cliente</label>
                <input type="radio" id="empleado" name="tipoUsuario" value="EMPLEADO" />
                <label for="empleado">Empleado</label>
            </div>

            <div id="saldo-container" style="display: none;" class="mb-3">
                <label for="saldo" class="form-label">Saldo inicial:</label>
                <input type="number" id="saldo" name="saldo" class="form-control" step="0.01" min="0" />
            </div>

            <div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </div>
        </form>
    </div>

    <!-- Usamos un script para manejar el input si elegimos tipo de usuario Cliente -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const radios = document.getElementsByName('tipoUsuario');
            const saldoContainer = document.getElementById('saldo-container');

            radios.forEach(radio => {
                radio.addEventListener('change', () => {
                    if (radio.value === 'CLIENTE' && radio.checked) {
                        saldoContainer.style.display = 'block'; // Mostrar el campo de saldo
                    } else {
                        saldoContainer.style.display = 'none'; // Ocultar el campo de saldo
                    }
                });
            });
        });
    </script>
</body>
</html>