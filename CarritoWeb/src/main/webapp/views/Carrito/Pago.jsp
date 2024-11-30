<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmación de Pago</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">
            <h2 class="mb-4 text-center">¿Desea confirmar su pago?</h2>

            
            <div class="border p-4 rounded shadow-sm">
                <form action="CarritoController?accion=finalizar" method="POST"> 

                    <div class="mb-3">
                        <label class="form-label">Saldo Actual:</label>
                        <p class="form-control-plaintext"><c:out value="${Cliente.getSaldo}"/></p>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Total:</label>
                        <p class="form-control-plaintext"><c:out value="${Carrito.verMontoTotal}"/></p>
                    </div>

                    <div class="mb-3 text-center">
                        <button type="submit" class="btn btn-primary w-100">Confirmar</button>
                    </div>
                </form>
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
