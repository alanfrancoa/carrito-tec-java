<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Factura B</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">

                <div class="bg-white p-4 border rounded shadow">

                    <div class="text-center border-bottom pb-3 mb-4">
                        <h2 class="fw-bold mb-0">Factura B</h2>
                        <p class="mb-0">Número de factura: <strong><c:out value="${numeroFactura}"/></strong></p>
                    </div>

                    <table class="table table-bordered table-striped text-center">
                        <thead class="table-light">
                            <tr>
                                <th>Detalle</th>
                                <th>Cantidad</th>
                                <th>Precio Unitario</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${detalleFactura}">
                                <tr>
                                    <td><c:out value="${item.producto.getNombre()}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.producto.getPrecio()}"/></td>
                                    <td><c:out value="${item.calcularPrecioTotal()}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="text-end mt-4">
                        <p class="fs-5 fw-bold">Total Final: $ <c:out value="${factura.total}"/></p>
                    </div>
                </div>

                <div class="text-center mt-4">
                 <!-- HACER QUE EL BOTON NO SOLO REDIRIJA SINO QUE LIMPE EL CARRITO -->
                    <a href="carrito?accion=limpiarCarrito" class="btn btn-secondary">Volver al Dashboard</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>