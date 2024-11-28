package repositories;

import java.util.ArrayList;
import java.util.List;

import modelos.usuarios.Empleado;
import modelos.usuarios.UsuarioBase;
import repositories.interfaces.UsuarioRepo;


public class UsuarioRepoSingleton implements UsuarioRepo{
	
	private static UsuarioRepoSingleton instanciaUnica; // Singleton
    private List<UsuarioBase> listaUsuarios;
    private List<String> mensajes;
    
	//Lista de usuarios empleados
    public List<UsuarioBase> getAllUsuarios() {
    	return new ArrayList<UsuarioBase>(this.listaUsuarios);
    	
	}

	public UsuarioRepoSingleton() {
		this.setUsuario(UsuarioBase.getInstance());
        this.mensajes = new ArrayList<>();

	}

    public static UsuarioRepoSingleton getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new UsuarioRepoSingleton();
        }
        return instanciaUnica;
    }
    
	    
	    //agregar un usuario
	    public void addUsuario(UsuarioBase usuario) {
	    	// Verificar si el código del artículo ya existe
	        boolean existe = this.listaUsuarios.stream()
	                .anyMatch(a -> a.getNombreUsuario().equals(usuario.getNombreUsuario()));

	        if (existe) {
	            // Guardar mensaje si el artículo ya existe
	            mensajes.add("Ya existe un usuario con el nombre" + usuario.getNombreUsuario());
	        } else {
	            // Agregar el artículo a la lista
	            this.listaUsuarios.add(usuario);
	            mensajes.add("Artículo agregado exitosamente: " + usuario.getNombreUsuario());
	        }
		}

	    //obtener un empleado por nombre de usuario
	    public UsuarioBase getByUsuario(String nombreUsuario) {
	    	
	    	return this.listaUsuarios.stream()
	                .filter(a -> a.getNombreUsuario().equals(nombreUsuario))
	                .findAny()
	                .orElse(null);
	    	
	    }

		public void setUsuario(UsuarioBase usuario) {
		}
		
	    public List<String> getMensajes() {
	        return new ArrayList<>(mensajes);
	    }

	    public void limpiarMensajes() {
	        mensajes.clear();
	    }

	    
}
