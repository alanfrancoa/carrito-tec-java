package modelos.usuarios;

import java.util.ArrayList;

import modelos.articulos.Articulo;

public class Empleado extends UsuarioBase{
	
	//atributo de la clase empleado
	private ArrayList<Articulo> listaArticulos;

	//constructor de la clase Empleado, hereda el super de la clase padre
	public Empleado(String nombreUsuario, String claveUsuario) {
		super(nombreUsuario, claveUsuario);
		this.listaArticulos = new ArrayList<>();
	}
	
	public ArrayList<Articulo> getListaArticulos(){
		return this.listaArticulos;
	}
	
	//Implementacion del metodo getTipoUsuario para empleado
	@Override
	public String getTipoUsuario() {
		return "EMPLEADO";
	}

	
	
}
