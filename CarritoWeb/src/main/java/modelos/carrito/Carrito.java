package modelos.carrito;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private static Carrito instanciaUnica;  //singleton
	private ArrayList<Renglon> listaCompra;

	public Carrito() {
		this.listaCompra = new ArrayList<>();
	}
	
	//metodo para obtener la unica instancia del carrito
	public static Carrito getInstance() {
		if(instanciaUnica == null) {
			instanciaUnica = new Carrito();
		}
		return instanciaUnica;
	}
	
	
	public void agregar(Renglon nuevoRenglon) {
		listaCompra.add(nuevoRenglon);
	}
	
	//ver contenido del carrito
	public List<Renglon> verCarrito(){
		return this.listaCompra;
	}
	
	//calcular monto total
	public double verMontoTotal() {
		double total = 0;
		for(Renglon renglon : listaCompra) {
			total += renglon.calcularPrecioTotal();
		}
		return total;
	}
	//ver finalizar compra y vaciar carrito...
	
}
