package repositories;

import java.util.ArrayList;
import java.util.List;

import modelos.articulos.Articulo;
import repositories.interfaces.ArticuloRepo;

public class ArticulosRepoSingleton implements ArticuloRepo {
	
	private static ArticulosRepoSingleton singleton;
	
	//repo que guarda obj singleton- poder acceder desde cualquier punto del programa//
	public static ArticulosRepoSingleton getInstance() {
		if(singleton == null) {
			singleton = new ArticulosRepoSingleton();
		}
		return singleton;
	}
	

	
	//lista provisoria para el create//
	
	
	private List<Articulo> listaArticulos;
	
	private ArticulosRepoSingleton() {
		
		this.listaArticulos = new ArrayList<Articulo>();
		Articulo articulo1 = new Articulo( 1,"ABC1" ,"Queso Crema", 2500, 100, "Lacteos");
		Articulo articulo2 = new Articulo( 2,"ABC2","Coca-cola", 3700, 1000, "Bebidas");
		Articulo articulo3 = new Articulo( 3,"ABC3","Oreos", 1700, 80, "Galletas");
		
		this.createArticulo(articulo1);
		this.createArticulo(articulo2);
		this.createArticulo(articulo3);
	}
	
	
	@Override
	public List<Articulo> getAllArticulos() {
		//copia de lista articulo//
		
		return new ArrayList<Articulo>(this.listaArticulos);
	}

	@Override
	public Articulo findArtByCod(String codigo_art) {
		return this.listaArticulos.stream()
			.filter( (a) -> a.getcodigo_art() == codigo_art)
			.findAny()
			.orElse(null);
				
	}

	@Override
	public void createArticulo(Articulo articulo) {
		//clase repo en memoria momentanea//
		
			int ultimaId= this.listaArticulos.stream()
					.map(Articulo::getId_articulo)
					.max(Integer::compare)
					.orElse(0);
			articulo.setId_articulo(ultimaId+1);
			this.listaArticulos.add(articulo);
	
	}

	@Override
	public void updateArticulo(Articulo articulo) {
		
		
	}

	@Override
	public void deleteArticulo(int id) {
		this.listaArticulos.removeIf( (a)-> a.getId_articulo() == id);		
	}

}
