<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmación de Compra</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <h2 class="mb-4 text-center text-success">Su pago fue confirmado</h2>

                <div class="border p-4 rounded shadow-sm text-center">
                    <p class="fs-5">¿Desea ver su factura?</p>
						<a href="carrito?accion=verFactura" class="btn btn-primary w-100">Ver mi factura</a>
				</div>

                <div class="mt-3 text-center">
                    <a href="ClienteController?action=Dashboard" class="btn btn-secondary w-100">Volver al Dashboard</a>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
