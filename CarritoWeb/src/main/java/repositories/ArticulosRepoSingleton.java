package repositories;

import java.util.ArrayList;
import java.util.List;

import modelos.articulos.Articulo;
import repositories.interfaces.ArticuloRepo;

public class ArticulosRepoSingleton implements ArticuloRepo {

    private static ArticulosRepoSingleton singleton;

    // Lista de artículos (almacenamiento temporal)
    private List<Articulo> listaArticulos;

    // Lista de mensajes
    private List<String> mensajes;

    // Constructor privado para Singleton
    private ArticulosRepoSingleton() {
        this.listaArticulos = new ArrayList<>();
        this.mensajes = new ArrayList<>();

        // Agregar algunos artículos de prueba
        Articulo articulo1 = new Articulo("ABC1", "Queso Crema", 2500, 100, "Lacteos");
        Articulo articulo2 = new Articulo("ABC2", "Coca-cola", 3700, 1000, "Bebidas");
        Articulo articulo3 = new Articulo("ABC3", "Oreos", 1700, 80, "Galletas");

        this.createArticulo(articulo1);
        this.createArticulo(articulo2);
        this.createArticulo(articulo3);
    }

    // Método para obtener la instancia única
    public static ArticulosRepoSingleton getInstance() {
        if (singleton == null) {
            singleton = new ArticulosRepoSingleton();
        }
        return singleton;
    }

    @Override
    public List<Articulo> getAllArticulos() {
        // Devolver una copia de la lista de artículos
        return new ArrayList<>(this.listaArticulos);
    }

    @Override
    public Articulo findArtByCod(String codigo_art) {
        // Buscar un artículo por su código
        return this.listaArticulos.stream()
                .filter(a -> a.getcodigo_art().equals(codigo_art))
                .findAny()
                .orElse(null);
    }

    @Override
    public void createArticulo(Articulo articulo) {
        // Verificar si el código del artículo ya existe
        boolean existe = this.listaArticulos.stream()
                .anyMatch(a -> a.getcodigo_art().equals(articulo.getcodigo_art()));

        if (existe) {
            // Guardar mensaje si el artículo ya existe
            mensajes.add("Ya existe un artículo con el código: " + articulo.getcodigo_art());
        } else {
            // Agregar el artículo a la lista
            this.listaArticulos.add(articulo);
            mensajes.add("Artículo agregado exitosamente: " + articulo.getcodigo_art());
        }
    }

    @Override
    public void updateArticulo(Articulo articulo) {
        // Buscar el artículo por su código
        Articulo existente = findArtByCod(articulo.getcodigo_art());

        if (existente != null) {
            // Actualizar los campos del artículo encontrado
            existente.setNombre(articulo.getNombre());
            existente.setPrecio(articulo.getPrecio());
            existente.setStock(articulo.getStock());
            existente.setRubro(articulo.getRubro());
            mensajes.add("Artículo actualizado correctamente: " + articulo.getcodigo_art());
        } else {
            mensajes.add("No se encontró el artículo con el código: " + articulo.getcodigo_art());
        }
    }
    @Override
    public void deleteArticulo(String codigo_art) {
        // Buscar el artículo por su código y eliminarlo si existe
        boolean eliminado = this.listaArticulos.removeIf(a -> a.getcodigo_art().equals(codigo_art));

        if (eliminado) {
            mensajes.add("Artículo eliminado correctamente: " + codigo_art);
        } else {
            mensajes.add("No se encontró el artículo con el código: " + codigo_art);
        }
    }


    // Métodos para gestionar mensajes
    public List<String> getMensajes() {
        return new ArrayList<>(mensajes); // Devolver copia de los mensajes
    }

    public void limpiarMensajes() {
        mensajes.clear(); // Limpiar mensajes después de mostrarlos
    }

}
