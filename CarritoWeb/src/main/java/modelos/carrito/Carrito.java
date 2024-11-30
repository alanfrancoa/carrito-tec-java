package modelos.carrito;

import java.util.ArrayList;
import java.util.List;

import modelos.compras.Compra;
import repositories.CompraRepoSingleton;


public class Carrito {

	// Singleton para única instancia
	private static Carrito instanciaUnica;

	// Lista de renglones (productos en el carrito)
	private ArrayList<Renglon> listaCompra;

	// Constructor privado para restringir la creación de instancias
	private Carrito() {
		this.listaCompra = new ArrayList<>();
	}

	/**
	 * Método para obtener la única instancia del carrito. Implementa el patrón
	 * Singleton.
	 */
	public static Carrito getInstance() {
		if (instanciaUnica == null) {
			instanciaUnica = new Carrito();
		}
		return instanciaUnica;
	}

	/**
	 * Agrega un nuevo renglón (producto) al carrito.
	 * 
	 * @param nuevoRenglon El renglón a agregar.
	 */
	public void agregar(Renglon nuevoRenglon) {
		listaCompra.add(nuevoRenglon);
	}

	/**
	 * Devuelve la lista completa de renglones en el carrito.
	 * 
	 * @return Lista de renglones.
	 */
	public List<Renglon> verCarrito() {
		return this.listaCompra;
	}

	/**
	 * Calcula el monto total del carrito sumando los precios totales de cada
	 * renglón.
	 * 
	 * @return Monto total del carrito.
	 */
	public double verMontoTotal() {
		double total = 0;
		for (Renglon renglon : listaCompra) {
			total += renglon.calcularPrecioTotal();
		}
		return total;
	}

	/**
	 * Finaliza la compra, mostrando el monto total y vaciando el carrito.
	 * 
	 * @return El monto total de la compra antes de vaciar el carrito.
	 */
	public double finalizarCompra() {
		
		double total = this.verMontoTotal();
		listaCompra.clear();
		return total;
	}

	public Compra confirmarCompra(String nombreCliente, String numeroFactura) {
        double total = this.verMontoTotal();
        Compra compra = new Compra(nombreCliente, new ArrayList<>(listaCompra), numeroFactura, total);

        // Guardar la compra en el repositorio
        CompraRepoSingleton.getInstance().agregarCompra(compra);

        // Vaciar el carrito
        listaCompra.clear();

        return compra;
    }
}
