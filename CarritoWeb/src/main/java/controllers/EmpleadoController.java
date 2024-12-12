package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import decorators.SessionDecorator;
import exceptions.UsuarioDeslogueadoException;
import interfaces.Usuario;
import modelos.articulos.Articulo;
import modelos.compras.Compra;
import modelos.usuarios.Cliente;
import modelos.usuarios.Empleado;
import modelos.usuarios.UsuarioBase;
import repositories.ArticulosRepoSingleton;
import repositories.CompraRepoSingleton;
import repositories.UsuarioRepoSingleton;

@WebServlet("/empleado")
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Repositorios Singleton
	private UsuarioRepoSingleton usuarioRepo;
	private ArticulosRepoSingleton articuloRepo;
	private CompraRepoSingleton compraRepo;

	/**
	 * Constructor del controlador
	 */
	public EmpleadoController() {
		super();
		// Inicializamos los repositorios
		this.usuarioRepo = UsuarioRepoSingleton.getInstance();
		this.articuloRepo = ArticulosRepoSingleton.getInstance();
		this.compraRepo = CompraRepoSingleton.getInstance();
	}

	/**
	 * Método que maneja las solicitudes GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtenemos la sesion actual
		HttpSession session = request.getSession();

		// Creamos el decorador
		SessionDecorator sessionDec = new SessionDecorator(session);

		try {

			Usuario usuario = sessionDec.getUsuarioLogueado();

			// Verificamos si el usuario está logueado
			if (usuario == null || !"EMPLEADO".equals(usuario.getTipoUsuario())) {
				response.sendRedirect("Login");
				return;
			}

			String accion = request.getParameter("accion");
			accion = Optional.ofNullable(accion).orElse("Dashboard");

			switch (accion) {
			case "empleadoDashboard":
				getEmpleadoDashboard(request, response);
				break;
			case "listarArticulos":
				listarArticulos(request, response);
				break;
			case "historialCompras":
				historialCompras(request, response);
				break;
			case "formularioUsuario":
				getFormularioUsuario(request, response);
				break;
			default:
				getEmpleadoDashboard(request, response);
			}
		} catch (UsuarioDeslogueadoException e) {
			response.sendRedirect("Login");
		}

	}

	private void getFormularioUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("views/usuario/newRegisterForm.jsp").forward(request, response);

	}

	private void getEmpleadoDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UsuarioDeslogueadoException {

		HttpSession session = request.getSession();

		SessionDecorator sessionDec = new SessionDecorator(session);

		// Obtenemos el usuario logeado
		UsuarioBase usuarioLog = sessionDec.getUsuarioLogueado();

		Empleado empleado = (Empleado) usuarioLog;

		session.setAttribute("usuario", empleado);

		request.getRequestDispatcher("views/usuario/empleadoDashboard.jsp").forward(request, response);

	}

	private void listarArticulos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Me traigo la lista de articulos
		List<Articulo> listArt = articuloRepo.getAllArticulos();

		request.setAttribute("listita", listArt);
		request.getRequestDispatcher("/views/articulos/index.jsp").forward(request, response);
	}

	private void historialCompras(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		List<Compra> compras = this.compraRepo.obtenerTodasLasCompras();
		
		request.setAttribute("compras", compras);
		
		request.getRequestDispatcher("/views/compras/HistorialComprasTotal.jsp").forward(request, response);
	}

	/**
	 * Método que maneja las solicitudes POST.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");

		switch (accion) {
		case "crearArticulo":
			crearArticulo(request, response);
			break;
		case "editarArticulo":
			editarArticulo(request, response);
			break;
		case "eliminarArticulo":
			eliminarArticulo(request, response);
			break;
		case "crearUsuario":
			crearUsuario(request, response);
			break;
		default:
			response.sendError(404);
		}
	}

	private void crearArticulo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String codigoArt = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		double precio = Double.parseDouble(request.getParameter("precio"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String rubro = request.getParameter("rubro");

		Articulo nuevoArticulo = new Articulo(codigoArt, nombre, precio, stock, rubro);
		articuloRepo.createArticulo(nuevoArticulo);

		response.sendRedirect("empleado?accion=listarArticulos");

	}

	private void editarArticulo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String codigoArt = request.getParameter("codigoArt");
		Articulo articulo = articuloRepo.findArtByCod(codigoArt);

		if (articulo != null) {
			articulo.setNombre(request.getParameter("nombre"));
			articulo.setPrecio(Double.parseDouble(request.getParameter("precio")));
			articulo.setStock(Integer.parseInt(request.getParameter("stock")));
			articulo.setRubro(request.getParameter("rubro"));
			articuloRepo.updateArticulo(articulo);
		}

		response.sendRedirect("empleado?accion=listarArticulos");

	}

	private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		articuloRepo.deleteArticulo(id);

		response.sendRedirect("empleado?accion=listarArticulos");

	}

	// === Métodos relacionados con usuarios en general ===
	private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String tipo = request.getParameter("tipo");
		UsuarioBase nuevoUsuario;

		if (tipo.equals("EMPLEADO")) {
			nuevoUsuario = new Empleado(nombre, pass);
		} else {
			nuevoUsuario = new Cliente(nombre, pass, 0);
		}

		usuarioRepo.addUsuario(nuevoUsuario);
		response.sendRedirect("empleado?accion=listarEmpleados");
	}

}
