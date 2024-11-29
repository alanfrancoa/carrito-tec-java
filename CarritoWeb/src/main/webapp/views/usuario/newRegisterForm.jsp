<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuevo usuario</title>
</head>
<body>

<h1>Registro de nuevo usuario</h1>

<form action="registroUsuario" method="post">

	<div >
	  	<label>
			Usuario:
			<input value="" name= "nombreUsuario"/>
		</label>
	</div>
	
	<div>
		<label>
			Contraseña:
			<input value="" name= "claveUsuario"/>
		</label>
	</div>
	
	<div> 
        <label>
        	Tipo de usuario:
            <input type="radio" name="tipoUsuario" value="Cliente" required /> Cliente
        </label>
        <label>
            <input type="radio" name="tipoUsuario" value="Empleado" /> Empleado
        </label>
    </div>

	<div>
	   <button type="submit" class="btn btn-primary">Registrar</button>
	</div>


</form>

</body>
</html>