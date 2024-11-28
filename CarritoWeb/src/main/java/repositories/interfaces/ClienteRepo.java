package repositories.interfaces;

import java.util.List;

import modelos.usuarios.Cliente;

public interface ClienteRepo {
	//Lista de usuarios empleados
    List<Cliente> getAllClientes();
    
    //obtener empleado por nombre de usuario
    public Cliente getCliente(String nombreUsuario);
    
    //agregar un empleado
    public void addCliente(Cliente empleado);
    
}
