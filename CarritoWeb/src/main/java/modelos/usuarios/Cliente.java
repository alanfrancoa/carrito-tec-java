package modelos.usuarios;

public class Cliente extends UsuarioBase{
	
	//atributos
	private double saldo;

	//constructor de la clase (hereda a traves de super los atributos del padre)
	public Cliente(String nombreUsuario, String claveUsuario, double saldo) {
		super(nombreUsuario, claveUsuario);
		this.saldo = saldo;
	}

	//getters y setters propios de la clase cliente
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	//implementamos metodo getTipoUsuario para el cliente
	@Override
	public String getTipoUsuario() {
		return "CLIENTE";
	}
	
	
	
	
}
