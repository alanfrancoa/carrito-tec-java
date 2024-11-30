package controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.usuarios.Cliente;
import modelos.usuarios.Empleado;
import modelos.usuarios.UsuarioBase;
import repositories.UsuarioRepoSingleton;

@WebServlet("/AltaUsuario")
public class NuevosUsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioRepoSingleton usuarioRepo;
	
    public NuevosUsuariosController() {
        super();
        this.usuarioRepo = UsuarioRepoSingleton.getInstance();
        
    }

    /*----------------------DO GET--------------------------*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("Sign-in");
		
		switch(accion) {
		case "Sign-in" -> mostrarRegistroUsuario(request, response);
		
		default -> response.sendError(404);
		}
		
		
	}

	private void mostrarRegistroUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("views/usuario/newRegisterForm.jsp").forward(request, response);
	}
	
    /*----------------------DO POST--------------------------*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("post-create");

		switch (accion) {
		case "post-create" -> registrarUsuario(request, response);
		default -> response.sendError(404);
		}
	}

	private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipoUsuario = request.getParameter("tipoUsuario");
		String nombreUsuario = request.getParameter("nombreUsuario");
		String claveUsuario = request.getParameter("claveUsuario");

		UsuarioBase nuevoUsuario;

		if ("CLIENTE".equals(tipoUsuario)) {
			
			String sSaldo = request.getParameter("saldo");
			double saldo = Double.parseDouble(sSaldo);
			nuevoUsuario = new Cliente(nombreUsuario, claveUsuario, saldo); // Crea un usuario que es de tipo CLIENTE
																			// con saldo

		} else if("EMPLEADO".equals(tipoUsuario)){
			nuevoUsuario = new Empleado(nombreUsuario, claveUsuario);
		} else {
			request.setAttribute("error", "Tipo de usuario inválido");
			request.getRequestDispatcher("views/usuario/newRegisterForm.jsp").forward(request, response);
			return;
		}

		usuarioRepo.addUsuario(nuevoUsuario); // agrega el nuevo usuario
		response.sendRedirect("AltaUsuario");

	}
}
