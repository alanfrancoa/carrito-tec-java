<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div class="container mt-5">
	
		<h1 class="mb-4">¡Bienvenido!</h1>
		
		<!-- Formulario de registro -->
		<form action="Login" method="post" class="h4 text-left mb-4">
		 	<input type="hidden" value="Auth" name="accion">
		
			<div class="mb-3"> 
				<label class="form-label">
					Usuario:<input value="" name= "nombreUsuario"/>
				</label>
				
			</div>
			<div class="mb-3"> 
				<label class="form-label">
					Clave:<input type="password" value="" name= "claveUsuario"/>
				</label>
				
			</div>
		
		<div>
			<button type="submit" class="btn btn-primary">Ingresar</button>
		</div>
		
		</form>
	</div>
</body>
</html>