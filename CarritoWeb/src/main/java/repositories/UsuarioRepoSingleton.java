package repositories;

import java.util.List;

import modelos.usuarios.UsuarioBase;
import repositories.interfaces.UsuarioRepo;


public class UsuarioRepoSingleton implements UsuarioRepo{

	 public List<UsuarioBase> getAllUsuarios() {
		return null;
	}
	    
	    //agregar un usuario
	    public void addUsuario(UsuarioBase nombreUsuario) {
		}

	    //obtener un empleado por nombre de usuario
	    public void obtenerEmpleado(UsuarioBase nombreUsuario) {
		}
	    
}
