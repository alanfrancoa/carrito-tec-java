<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Articulo</title>
</head>
<body>

<h1> Editar</h1>

<form action="" method="post">

	<p> 
		Codigo:<input value="${articulo.codigo_art }" name= "codigo"/>
	</p>
	<p> 
		Nombre:<input value="${articulo.nombre }" name= "nombre"/>
	</p>
	<p> 
		Precio:<input value="${articulo.precio }" name= "precio"/>
	</p>
	<p> 
		Stock:<input value="${articulo.stock }" name= "stock"/>
	</p>
	<p> 
		Rubro:<input value="${articulo.rubro }" name= "rubro"/>
	</p>

<input type="submit" value="Editar Articulo">

</form>

</body>
</html>