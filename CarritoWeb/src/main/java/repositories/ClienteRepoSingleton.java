package repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import modelos.usuarios.Cliente;
import repositories.interfaces.ClienteRepo;

public class ClienteRepoSingleton implements ClienteRepo {
	
	private List<Cliente> listaClientes;

	//Lista de usuarios empleados
    public List<Cliente> getAllClientes() {
    	return new ArrayList<Cliente>(this.listaClientes);
	}
    
	//obtener empleado por nombre de usuario
    public Cliente getCliente(String usuarioCliente) {
		return null;
	}
    
    //agregar un empleado
    public void addCliente(Cliente cliente) {
	}
    
}
