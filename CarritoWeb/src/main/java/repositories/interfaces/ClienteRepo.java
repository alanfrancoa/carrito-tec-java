package repositories.interfaces;

import java.util.List;

import modelos.usuarios.Cliente;

public interface ClienteRepo {
	//Lista de usuarios clientes
    List<Cliente> getAllClientes();
    
    //obtener cliente 
    public Cliente getCliente(String nombreUsuario);
    
    //agregar un cliente
    public void addCliente(Cliente empleado);
    
    //modulo saldo
    void transferirSaldo(String usuarioOrigen, String usuarioDestino, double monto);
    void ingresarSaldo(String nombreUsuario, double monto);
    void retirarSaldo(String nombreUsuario, double monto);
}
