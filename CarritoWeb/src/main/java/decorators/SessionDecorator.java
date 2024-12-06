package decorators;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import exceptions.UsuarioDeslogueadoException;
import interfaces.Usuario;
import modelos.usuarios.UsuarioBase;

public class SessionDecorator {

	private HttpSession session;

	public SessionDecorator(HttpSession session) {
		super();
		this.session = session;
	}

	// Metodo para obtener el usuario logueado
	public UsuarioBase getUsuarioLogueado() throws UsuarioDeslogueadoException {

		UsuarioBase usuarioNullable = (UsuarioBase) session.getAttribute("usuario");

		UsuarioBase usuarioLog = Optional.ofNullable(usuarioNullable).orElseThrow(() -> new UsuarioDeslogueadoException());

		return usuarioLog;
	}

	public boolean esCliente() {
	    try {
	        return "CLIENTE".equals(this.getUsuarioLogueado().getTipoUsuario());
	    } catch (UsuarioDeslogueadoException e) {
	        return false; // o manejar el error de otra manera según la lógica de tu aplicación
	    }
	}

	public boolean esEmpleado() {
	    try {
	        return "EMPLEADO".equals(this.getUsuarioLogueado().getTipoUsuario());
	    } catch (UsuarioDeslogueadoException e) {
	        return false; // o manejar el error de otra manera
	    }
	}

}
