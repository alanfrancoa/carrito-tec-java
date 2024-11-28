package modelos.articulos;

public class Articulo {
	//Atributos
	private int id_articulo;
	private String codigo_art;
	private String nombre;
	private double precio;
	private int stock;
	private String rubro;
	
	//Constructor
	public Articulo(int id_articulo,String codigo_art, String nombre, double precio, int stock, String rubro) {
		super();
		this.id_articulo = id_articulo;
		this.codigo_art = codigo_art;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.rubro = rubro;
	}
	
	
	//getters y setters
	public int getId_articulo() {
		return id_articulo;
	}
	public String getcodigo_art() {
		return codigo_art;
	}
	public String getNombre() {
		return nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public int getStock() {
		return stock;
	}
	public String getRubro() {
		return rubro;
	}
	public void setId_articulo(int id_articulo) {
		this.id_articulo = id_articulo;
	}
	public void setcodigo_art(String codigo_art) {
		this.codigo_art = codigo_art;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	
	
	
}
