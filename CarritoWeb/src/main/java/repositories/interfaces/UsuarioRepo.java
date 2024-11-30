package repositories.interfaces;

import java.util.List;

import modelos.usuarios.UsuarioBase;

public interface UsuarioRepo {
	
	    List<UsuarioBase> getAllUsuarios();
	    
	    public UsuarioBase getUsuario(String nombreUsuario);
	    
	    //agregar un usuario
	    public void addUsuario(UsuarioBase nuevoUsuario);
	    
	    public void deleteUsuario(UsuarioBase nuevoUsuario);
}
