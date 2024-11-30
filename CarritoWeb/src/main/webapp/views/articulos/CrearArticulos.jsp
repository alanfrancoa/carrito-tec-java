<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cargar articulo</title>
</head>
<body>

<h1>Alta</h1>

<form action="articulos" method="post">

	<p> 
		Codigo:<input value="" name= "codigo"/>
	</p>
	<p> 
		Nombre:<input value="" name= "nombre"/>
	</p>
	<p> 
		Precio:<input value="" name= "precio"/>
	</p>
	<p> 
		Stock:<input value="" name= "stock"/>
	</p>
	<p> 
		Rubro:<input value="" name= "rubro"/>
	</p>

<input type="submit" value="Crear">

</form>

</body>
</html>