package controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.usuarios.Cliente;
import modelos.usuarios.UsuarioBase;
import repositories.UsuarioRepoSingleton;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioRepoSingleton usuarioRepo;

	public LoginController() {
		this.usuarioRepo = UsuarioRepoSingleton.getInstance();

	}

	/*----------------------DO GET--------------------------*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("Login");

		switch (accion) {
		case "Login" -> mostrarLoginUsuario(request, response);
		case "Logout" -> logoutUsuario(request, response);
		default -> response.sendError(404);
		}
	}

	private void mostrarLoginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/usuario/registerForm.jsp").forward(request, response); // Redirije a la
																									// vista
																									// correspondiente
	}

	/*----------------------DO POST--------------------------*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("Auth");

		switch (accion) {
		case "Auth" -> loginUsuario(request, response);
		default -> response.sendError(404);
		}

	}

	/*----------------------loginUsuario--------------------------*/
	private void loginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// Tomamos los valores del input del JSP
		String nombreUsuario = request.getParameter("nombreUsuario");
		String claveUsuario = request.getParameter("claveUsuario");

		// Buscamos el usuario
		UsuarioBase usuarioEncontrado = usuarioRepo.getAllUsuarios().stream()
				.filter(u -> u.getNombreUsuario().equals(nombreUsuario)).findFirst().orElse(null);

		if (usuarioEncontrado != null && usuarioEncontrado.getClaveUsuario().equals(claveUsuario)) {

			String tipoUsuario = usuarioEncontrado.getTipoUsuario();

			if (usuarioEncontrado instanceof Cliente cliente) {

				HttpSession session = request.getSession();

				session.setAttribute("usuario", cliente);

				response.sendRedirect(request.getContextPath() + "/views/usuario/clienteDashboard.jsp");

			} else if ("EMPLEADO".equals(tipoUsuario)) {

				// Guardamos el usuario en la sesión
				HttpSession session = request.getSession();

				// Le pasamos el usuario encontrado
				session.setAttribute("usuario", usuarioEncontrado);

				response.sendRedirect(request.getContextPath() + "/views/usuario/empleadoDashboard.jsp");
			}

		} else {
			// Si el usuario no existe o la contraseña es incorrecta, que nos muestre un
			// mensaje de error
			response.sendRedirect("Login?error=Usuario o clave incorrectos");
		}

	}

	/*----------------------logoutUsuario--------------------------*/
	private void logoutUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// Invalida la sesión del usuario
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		response.sendRedirect(request.getContextPath() + "/Login?accion=Login");
	}

}
