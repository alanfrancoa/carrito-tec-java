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
		super();
		this.usuarioRepo = UsuarioRepoSingleton.getInstance();

	}

	/*----------------------DO GET--------------------------*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("Login");

		switch (accion) {
		case "Login" -> mostrarLoginUsuario(request, response);

		default -> response.sendError(404);
		}
	}

	

	private void mostrarLoginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/usuario/registerForm.jsp").forward(request, response); // Redirije a la vista
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
	private void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nombreUsuario = request.getParameter("nombreUsuario");
		String claveUsuario = request.getParameter("claveUsuario");

		UsuarioBase usuarioIngresado = usuarioRepo.getUsuario(nombreUsuario); // se usa el repo singleton para buscar el
																				// nombre de usuario

		if (usuarioIngresado != null && usuarioIngresado.getClaveUsuario().equals(claveUsuario)) {

			HttpSession session = request.getSession(); // Crea la sesión para el usuario que se logee
			session.setAttribute("usuarioLoggeado", usuarioIngresado.getNombreUsuario());
			
			if (usuarioIngresado.getTipoUsuario().equals("CLIENTE")) {
				
				// Si el tipo de usuario es CLIENTE, hacer casting a Cliente para obtener saldo
	            Cliente cliente = (Cliente) usuarioIngresado; 
	            double saldoCliente = cliente.getSaldo();

	            // Puedes almacenar el saldo en la sesión
	            session.setAttribute("saldoCliente", saldoCliente);
	            
				response.sendRedirect("views/usuario/clienteDashboard.jsp");
			} else {
				response.sendRedirect("views/usuario/empleadoDashboard.jsp");
			}

		} else {
			request.setAttribute("error", "Usuario o clave incorrectos");
	        request.getRequestDispatcher("views/usuario/registerForm.jsp").forward(request, response);
		}

	}

}
