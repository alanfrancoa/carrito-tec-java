package controllers;

import java.io.IOException;
import java.util.List;
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
import modelos.usuarios.Cliente;
import modelos.usuarios.Empleado;
import modelos.usuarios.UsuarioBase;
import repositories.ArticulosRepoSingleton;
import repositories.UsuarioRepoSingleton;

@WebServlet("/EmpleadoController")
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Repositorios Singleton
	private UsuarioRepoSingleton usuarioRepo;
	private ArticulosRepoSingleton articuloRepo;

	/**
	 * Constructor del controlador
	 */
	public EmpleadoController() {
		super();
		// Inicializamos los repositorios
		this.usuarioRepo = UsuarioRepoSingleton.getInstance();
		this.articuloRepo = ArticulosRepoSingleton.getInstance();
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
				response.sendRedirect(request.getContextPath() + "/views/usuario/registerForm.jsp");
				return;
			}

			String accion = request.getParameter("accion");
			accion = (accion != null) ? accion : "empleadoDashboard";

			switch (accion) {
			case "listarEmpleados":
				listarEmpleados(request, response);
				break;
			case "empleadoDashboard":
				getEmpleadoDashboard(request, response);
				break;
			case "listarClientes":
				listarClientes(request, response);
				break;
			case "listarArticulos":
				listarArticulos(request, response);
				break;
			case "historialCompras":
				historialCompras(request, response);
				break;
			default:
				getEmpleadoDashboard(request, response);
			}
		} catch (UsuarioDeslogueadoException e) {
			response.sendRedirect(request.getContextPath() + "/views/usuario/registerForm.jsp");
		}

	}

	private void getEmpleadoDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/usuario/empleadoDashboard.jsp").forward(request, response);

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
		case "crearCliente":
			crearCliente(request, response);
			break;
		case "eliminarCliente":
			eliminarCliente(request, response);
			break;
		case "crearUsuario":
			crearUsuario(request, response);
			break;
		case "eliminarUsuario":
			eliminarUsuario(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	// === Métodos relacionados con empleados ===
	private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Empleado> empleados = usuarioRepo.getAllUsuarios().stream()
				.filter(usuario -> usuario.getTipoUsuario().equals("EMPLEADO")).map(usuario -> (Empleado) usuario)
				.collect(Collectors.toList());

		request.setAttribute("empleados", empleados);
		request.getRequestDispatcher("/views/usuario/listarEmpleados.jsp").forward(request, response);
	}

	// === Métodos relacionados con clientes ===
	private void listarClientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Cliente> clientes = usuarioRepo.getAllUsuarios().stream()
				.filter(usuario -> usuario.getTipoUsuario().equals("CLIENTE")).map(usuario -> (Cliente) usuario)
				.collect(Collectors.toList());

		request.setAttribute("clientes", clientes);
		request.getRequestDispatcher("/views/usuario/listarClientes.jsp").forward(request, response);
	}

	private void crearCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");

		Cliente nuevoCliente = new Cliente(nombre, email, 0);
		usuarioRepo.addUsuario(nuevoCliente);

		response.sendRedirect("EmpleadoController?accion=listarClientes");
	}

	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombreCliente = request.getParameter("nombreCliente");
		UsuarioBase cliente = usuarioRepo.getUsuario(nombreCliente);
		usuarioRepo.deleteUsuario(cliente);

		response.sendRedirect("EmpleadoController?accion=listarClientes");
	}

	// === Métodos relacionados con artículos ===
	private void listarArticulos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Articulo> articulos = articuloRepo.getAllArticulos();

		request.setAttribute("articulos", articulos);
		request.getRequestDispatcher("/views/articulos/index.jsp").forward(request, response);
	}

	private void crearArticulo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String codigoArt = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		double precio = Double.parseDouble(request.getParameter("precio"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String rubro = request.getParameter("rubro");

		Articulo nuevoArticulo = new Articulo(codigoArt, nombre, precio, stock, rubro);
		articuloRepo.createArticulo(nuevoArticulo);

		response.sendRedirect("EmpleadoController?accion=listarArticulos");

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

		response.sendRedirect("EmpleadoController?accion=listarArticulos");

	}

	private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		articuloRepo.deleteArticulo(id);

		response.sendRedirect("EmpleadoController?accion=listarArticulos");

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
		response.sendRedirect("EmpleadoController?accion=listarEmpleados");
	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombreUsuario = request.getParameter("id"); // Cambié de 'id' a 'nombreUsuario'
		UsuarioBase usuario = usuarioRepo.getUsuario(nombreUsuario); // Obtener el usuario por nombre

		if (usuario != null) {
			usuarioRepo.deleteUsuario(usuario); // Eliminar el usuario
		}

		response.sendRedirect("EmpleadoController?accion=listarEmpleados"); // Redirigir a la lista de empleados
	}

	// === Método relacionado con el historial de compras ===
	private void historialCompras(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Aquí deberías implementar cómo obtener el historial desde tu repositorio o
		// servicio.
		// redirigimos a la vista de historial
		request.getRequestDispatcher("/views/compras/HistorialComprasTotal.jsp").forward(request, response);
	}
}
