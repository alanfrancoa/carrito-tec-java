package modelos.usuarios;

import interfaces.Usuario;

public abstract class UsuarioBase implements Usuario{
	//atributos
	private String nombreUsuario;
	private String claveUsuario;
	
	//constructor
	public UsuarioBase(String nombreUsuario, String claveUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.claveUsuario = claveUsuario;
	}
	
	//setters
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}
	
	//getters (implementados por medio de interfaz usuario)
	@Override
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	@Override
	public String getClaveUsuario() {
		return this.claveUsuario;
	}
	
	//Metodo abstracto implementado por clases hijas. 
	@Override
	public abstract String getTipoUsuario();

	//toString
	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + this.nombreUsuario + ", getTipoUsuario()=" + this.getTipoUsuario() + "]";
	}

	public String getUsuario() {
		return this.nombreUsuario;
	}

}
	
	
