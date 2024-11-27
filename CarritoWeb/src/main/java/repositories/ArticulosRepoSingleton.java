package repositories;

import java.util.ArrayList;
import java.util.List;

import modelos.articulos.Articulo;
import repositories.interfaces.ArticuloRepo;

public class ArticulosRepoSingleton implements ArticuloRepo {

	
	//lista provisoria para el create//
	
	
	private List<Articulo> listaArticulos;
	
	public ArticulosRepoSingleton() {
		
		this.listaArticulos = new ArrayList<Articulo>();
		Articulo articulo1 = new Articulo(1, "Queso Crema", 2500, 100, "Lacteos");
		Articulo articulo2 = new Articulo(2, "Coca-cola", 3700, 1000, "Bebidas");
		Articulo articulo3 = new Articulo(3, "Oreos", 1700, 80, "Galletas");
		
		this.createArticulo(articulo1);
		this.createArticulo(articulo2);
		this.createArticulo(articulo3);
	}
	
	
	@Override
	public List<Articulo> getAllArticulos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articulo findArtByCod(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticulo(int id) {
		// TODO Auto-generated method stub
		
	}

}
