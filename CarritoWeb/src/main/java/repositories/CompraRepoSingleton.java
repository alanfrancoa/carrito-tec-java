package repositories;

import java.util.ArrayList;
import java.util.List;

import modelos.compras.Compra;
import repositories.interfaces.CompraRepo;

public class CompraRepoSingleton implements CompraRepo{
	
    private static CompraRepoSingleton instance;
	private Compra compra;
    private List<Compra> comprasRealizadas;

    public CompraRepoSingleton() {
        this.comprasRealizadas = new ArrayList<>();
    }

    public static synchronized CompraRepo getInstance() {
        if (instance == null) {
            instance = new CompraRepoSingleton();
        }
        return instance;
    }

    // Agregar una nueva compra
    public void agregarCompra(Compra compra) {
        comprasRealizadas.add(compra);
    }

    // Obtener todas las compras realizadas
    public List<Compra> obtenerTodasLasCompras() {
        return new ArrayList<>(comprasRealizadas);
    }

    // Obtener compras por cliente
    public List<Compra> obtenerComprasPorCliente(String nombreCliente) {
        return comprasRealizadas.stream()
                .filter(compra -> compra.getNombreCliente().equalsIgnoreCase(nombreCliente))
                .toList(); // Devuelve una lista con las compras del cliente.
    }
}
