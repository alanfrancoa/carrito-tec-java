package repositories.interfaces;

import java.util.List;

import modelos.usuarios.UsuarioBase;

public interface UsuarioRepo {
	
	    List<UsuarioBase> getAllUsuarios();
	    
	    //agregar un usuario
	    public void addUsuario(UsuarioBase nombreUsuario);

	    //obtener un empleado por nombre de usuario
	    public void obtenerEmpleado(UsuarioBase nombreUsuario);
}
