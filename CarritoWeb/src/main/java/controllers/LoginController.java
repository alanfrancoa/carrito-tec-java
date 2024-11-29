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

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioRepoSingleton usuarioRepo;

	public LoginController() {
		super();

	}

	/*----------------------DO GET--------------------------*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("Log-in");

		switch (accion) {
		case "Log-in" -> mostrarLoginUsuario(request, response);

		default -> response.sendError(404);
		}
	}

	

	private void mostrarLoginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/usuario/loginForm.jsp").forward(request, response); // Redirije a la vista
																								// correspondiente
	}

	/*----------------------DO POST--------------------------*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("Log-in");

		switch (accion) {
		case "Sign-in" -> registrarUsuario(request, response);
		case "Log-in" -> loginUsuario(request, response);
		default -> response.sendError(404);
		}

	}

	/*----------------------loginUsuario--------------------------*/
	private void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nombreUsuario = request.getParameter("nombreUsuario");
		String claveUsuario = request.getParameter("claveUsuario");

		UsuarioBase usuarioIngresado = usuarioRepo.getUsuario(nombreUsuario); // se usa el repo singleton para buscar el
																				// nombre de usuario

		if (usuarioIngresado != null && usuarioIngresado.getClaveUsuario().equals(claveUsuario)) {

			HttpSession session = request.getSession(); // Crea la sesión para el usuario que se logee
			session.setAttribute("usuarioLoggeado", usuarioIngresado);
			if (usuarioIngresado.getTipoUsuario().equals("CLIENTE")) {
				response.sendRedirect("views/usuario/clienteDashboard.jsp");
			} else {
				response.sendRedirect("views/usuario/empleadoDashboard.jsp");
			}

		} else {
			request.setAttribute("error", "Usuario o clave incorrectos");
	        request.getRequestDispatcher("views/usuario/registerForm.jsp").forward(request, response);
		}

	}
	/*----------------------registrarUsuario--------------------------*/

	private void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipoUsuario = request.getParameter("tipoUsuario");
		String nombreUsuario = request.getParameter("nombreUsuario");
		String claveUsuario = request.getParameter("claveUsuario");

		UsuarioBase nuevoUsuario;

		if ("CLIENTE".equals(tipoUsuario)) {
			double saldo = Double.parseDouble(request.getParameter("Saldo"));
			nuevoUsuario = new Cliente(nombreUsuario, claveUsuario, saldo); // Crea un usuario que es de tipo CLIENTE
																			// con saldo

		} else {
			request.setAttribute("error", "Tipo de usuario inválido");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

		usuarioRepo.addUsuario(nuevoUsuario); // agrega el nuevo usuario
		response.sendRedirect("UsuarioController?action=Log-in");

	}
}
