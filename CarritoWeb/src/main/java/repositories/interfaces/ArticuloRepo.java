package repositories.interfaces;

import java.util.List;

import modelos.articulos.Articulo;

public interface ArticuloRepo {
	
	//Metodos CRUD de Articulo//
	
	public List<Articulo> getAllArticulos();
	
	public Articulo findArtByCod(String codigo_art);
	
	public void createArticulo(Articulo articulo);
	
	public void updateArticulo(Articulo articulo);
	
	public void deleteArticulo(int id);
	
	//To check: si es necesario metodo updateStock por relacion del editArt//
}
