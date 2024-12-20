package repositories;

import java.util.ArrayList;
import java.util.List;

import modelos.usuarios.Cliente;
import modelos.usuarios.Empleado;
import modelos.usuarios.UsuarioBase;
import repositories.interfaces.UsuarioRepo;

public class UsuarioRepoSingleton implements UsuarioRepo {

	private static UsuarioRepoSingleton instanciaUnica; // Singleton

	private List<UsuarioBase> listaUsuarios;

	private List<String> mensajes;

	public UsuarioRepoSingleton() {
		this.listaUsuarios = new ArrayList<>();
		this.mensajes = new ArrayList<>();

		// Instanciamos dos Usuarios de tipo Cliente y Empleado
		Empleado empleado = new Empleado("Matias", "1234");
		Cliente cliente = new Cliente("Chicho", "1234", 100000.00);
		Cliente cliente2 = new Cliente("Sultan", "1234", 5000.00);

		this.listaUsuarios.add(cliente);
		this.listaUsuarios.add(empleado);
		this.listaUsuarios.add(cliente2);
	}

	public static UsuarioRepoSingleton getInstance() {
		if (instanciaUnica == null) {
			instanciaUnica = new UsuarioRepoSingleton();
		}
		return instanciaUnica;
	}

	// Lista de usuarios empleados
	public List<UsuarioBase> getAllUsuarios() {
		return new ArrayList<UsuarioBase>(this.listaUsuarios);

	}

	// agregar un usuario
	@Override
	public void addUsuario(UsuarioBase usuario) {
		// Verificar si el código del artículo ya existe
		boolean existe = this.listaUsuarios.stream()
				.anyMatch(a -> a.getNombreUsuario().equals(usuario.getNombreUsuario()));

		if (existe) {

			mensajes.add("Ya existe un usuario con el nombre" + usuario.getNombreUsuario());
		} else {

			this.listaUsuarios.add(usuario);
			mensajes.add("Usuario agregado exitosamente: " + usuario.getNombreUsuario());
		}
	}

	// obtener por nombre de usuario
	@Override
	public UsuarioBase getUsuario(String nombreUsuario) {

		return this.listaUsuarios.stream().filter(a -> a.getNombreUsuario().equals(nombreUsuario)).findAny()
				.orElse(null);
	}

	public List<String> getMensajes() {
		return new ArrayList<>(mensajes);
	}

	public void limpiarMensajes() {
		mensajes.clear();
	}

	@Override
	public void deleteUsuario(UsuarioBase usuario) {
		listaUsuarios.removeIf(u -> u.getNombreUsuario().equals(usuario.getNombreUsuario()));
	}

}
