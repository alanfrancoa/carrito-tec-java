package modelos.carrito;

import modelos.articulos.Articulo;

public class Renglon {
	//atributos
	private int cantidad;
	private Articulo producto;
	
	//constructor
	public Renglon(int cantidad, Articulo producto) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
	}

	//getters y setters
	public int getCantidad() {
		return cantidad;
	}

	public Articulo getProducto() {
		return producto;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setProducto(Articulo producto) {
		this.producto = producto;
	}
	
	//Metodo para calcular el precio del producto
	public double calcularPrecioTotal() {
		double precioTotal = this.cantidad * this.producto.getPrecio();
		return precioTotal;
	}
	
	
}
