package repositories.interfaces;

import java.util.List;

import modelos.carrito.Renglon;
import modelos.compras.Compra;

public interface CompraRepo {
	void agregarCompra(Compra compra);
	List<Renglon>obtenerTodasLasCompras();
	List<Compra> obtenerComprasPorCliente(String nombreCliente);
	
}
