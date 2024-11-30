package repositories;

import java.util.ArrayList;
import java.util.List;

import modelos.carrito.Carrito;
import modelos.carrito.Renglon;
import repositories.interfaces.CarritoRepo;

public class CarritoRepoSingleton implements CarritoRepo {

	private static CarritoRepoSingleton instanciaUnica; // Singleton
    private Carrito carrito;
    private List<Carrito> historialVentas; // Lista de carritos finalizados

	public CarritoRepoSingleton() {
		this.carrito = Carrito.getInstance();
        this.historialVentas = new ArrayList<>();
	}

	
	/**
     * Obtiene la instancia única del repositorio.
     * @return Instancia de CarritoRepoSingleton.
     */
    public static CarritoRepoSingleton getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new CarritoRepoSingleton();
        }
        return instanciaUnica;
    }
    
	@Override
	public void agregar(Renglon nuevoRenglon) {
		carrito.agregar(nuevoRenglon);
	}

	@Override
	public List<Renglon> verCarrito() {
		return carrito.verCarrito();
	}

	@Override
	public double verMontoTotal() {
		return carrito.verMontoTotal();
	}

	@Override
	public double finalizarCompra() {
		return carrito.finalizarCompra();
	}
	
	/*historial de ventas*/
	@Override	
	public void agregarVenta(Carrito venta) {
	    this.historialVentas.add(venta);
	}
	
	@Override
    public List<Carrito> getHistorialVentas() {
        return new ArrayList<>(historialVentas); // Retorna una copia para evitar modificaciones externas
    }
}
