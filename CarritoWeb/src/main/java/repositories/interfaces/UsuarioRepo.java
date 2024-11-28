package repositories.interfaces;

import java.util.List;

import modelos.usuarios.UsuarioBase;

public interface UsuarioRepo {
	
	    List<UsuarioBase> getAllUsuarios();
	    
	    public UsuarioBase getUsuario();
	    
	    //agregar un usuario
	    public void addUsuario(UsuarioBase nombreUsuario);

	    //obtener un empleado por nombre de usuario
	    public void getUsuario(UsuarioBase nombreUsuario);
	    
	    public void setUsuario(UsuarioBase usuario);
}
