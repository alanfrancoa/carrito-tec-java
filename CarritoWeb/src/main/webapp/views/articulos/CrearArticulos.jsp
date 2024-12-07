<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cargar Artículo</title>
    <!-- Incluir Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Alta de Artículo</h1>

    <form action="articulos" method="post">
        <input type="hidden" value="CrearArticulos" name="accion" />

        <div class="form-group">
            <label for="codigo">Código</label>
            <input type="text" class="form-control" id="codigo" name="codigo" placeholder="Ingrese el código del artículo" required>
        </div>

        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre del artículo" required>
        </div>

        <div class="form-group">
            <label for="precio">Precio</label>
            <input type="number" class="form-control" id="precio" name="precio" placeholder="Ingrese el precio del artículo" required>
        </div>

        <div class="form-group">
            <label for="stock">Stock</label>
            <input type="number" class="form-control" id="stock" name="stock" placeholder="Ingrese el stock del artículo" required>
        </div>

        <div class="form-group">
            <label for="rubro">Rubro</label>
            <input type="text" class="form-control" id="rubro" name="rubro" placeholder="Ingrese el rubro del artículo" required>
        </div>
		<div class="d-flex gap-3" >
			<button type="submit" class="btn btn-success w-25">Crear</button>    
	        <a href="empleado?accion=listarArticulos" class="btn btn-danger btn-block w-25 ml-2">Cancelar</a>   
        </div>
    </form>
   
</div>
     

<!-- Incluir JavaScript de Bootstrap (opcional para funcionalidades dinámicas) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
