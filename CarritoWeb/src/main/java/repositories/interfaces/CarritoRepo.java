package repositories.interfaces;

import java.util.List;

import modelos.carrito.Carrito;
import modelos.carrito.Renglon;

public interface CarritoRepo {

	void agregar(Renglon nuevoRenglon);
	List<Renglon> verCarrito();
	double verMontoTotal();
	double finalizarCompra();

}
