<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuevo usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<h1>Registro de nuevo usuario</h1>

<form action="AltaUsuario" method="post" class="h4 text-left mb-4">
<input type="hidden" value="post-create" name="accion" >

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
            <input type="radio" name="tipoUsuario" value="Empleado" /> EMpleado
        </label>
	</div>
	
	<div id="saldo-container" style="display:none;">
    <label>
        Saldo inicial:
        <input type="number" name="Saldo" step="0.01" min="0" />
    </label>
</div>
        

	<div>
	   <button type="submit" class="btn btn-primary">Registrar</button>
	</div>
	

</form>

<script>
	    const radios = document.getElementsByName('tipoUsuario');
	    const saldoContainer = document.getElementById('saldo-container');
	
	    radios.forEach(radio => {
	        radio.addEventListener('change', () => {
	            if (radio.value === 'Cliente') {
	                saldoContainer.style.display = 'block';
	            } else {
	                saldoContainer.style.display = 'none';
	            }
	        });
	    });
	</script>
</body>
</html>