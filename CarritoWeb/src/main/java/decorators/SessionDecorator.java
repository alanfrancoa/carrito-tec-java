package decorators;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import exceptions.UsuarioDeslogueadoException;
import interfaces.Usuario;

public class SessionDecorator {

	private HttpSession session;

	public SessionDecorator(HttpSession session) {
		super();
		this.session = session;
	}

	// Metodo para obtener el usuario logueado
	public Usuario getUsuarioLogueado() throws UsuarioDeslogueadoException {

		Usuario usuarioNullable = (Usuario) session.getAttribute("usuario");

		Usuario usuarioLog = Optional.ofNullable(usuarioNullable).orElseThrow(() -> new UsuarioDeslogueadoException());

		return usuarioLog;
	}

	// Metodos para validar el tipo de cliente
	public boolean esCliente() throws UsuarioDeslogueadoException {
		return "CLIENTE".equals(this.getUsuarioLogueado().getTipoUsuario());
	}

	public boolean esEmpleado() throws UsuarioDeslogueadoException {
		return "EMPLEADO".equals(this.getUsuarioLogueado().getTipoUsuario());
	}

}
