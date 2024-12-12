package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import decorators.SessionDecorator;
import exceptions.UsuarioDeslogueadoException;
import modelos.articulos.Articulo;
import modelos.carrito.Carrito;
import modelos.carrito.Renglon;
import modelos.compras.Compra;
import modelos.usuarios.Cliente;
import modelos.usuarios.Empleado;
import modelos.usuarios.UsuarioBase;
import repositories.ArticulosRepoSingleton;
import repositories.CompraRepoSingleton;
import repositories.interfaces.CompraRepo;

@WebServlet("/carrito")
public class CarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ArticulosRepoSingleton articuloRepo;
	private static CompraRepoSingleton compraRepo;
	

	// ------------------------ Métodos del Servlet ------------------------ //

	// Obtener o crear el carrito actual de sesión
	private Carrito obtenerCarritoDeSesion(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Carrito carritoActual = (Carrito) session.getAttribute("carrito");

		if (carritoActual == null) {
			carritoActual = Carrito.getInstance();
			session.setAttribute("carrito", carritoActual);
		}
		return carritoActual;
	}

	// Métodos para gestionar mensajes en la sesión
	private List<String> obtenerMensajes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<String> mensajes = (List<String>) session.getAttribute("mensajes");
		if (mensajes == null) {
			mensajes = new ArrayList<>();
			session.setAttribute("mensajes", mensajes);
		}
		return mensajes;
	}

	private void agregarMensaje(HttpServletRequest request, String mensaje) {
		List<String> mensajes = obtenerMensajes(request);
		mensajes.add(mensaje);
	}

	private void limpiarMensajes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("mensajes");
	}

	// Metodo para generar un numero unico para la factura
	// Generar un número único de factura
	private String generarNumeroFactura() {
		return "FAC-" + System.currentTimeMillis(); // Usa un timestamp para garantizar unicidad
	}

	// ------------------------ DO GET ------------------------ //
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("index");

		switch (accion) {
		case "index" -> mostrarCarrito(request, response);
		/* case "total" -> mostrarMontoTotal(request, response); */
		case "agregar" -> mostrarVistaAgregar(request, response);
		case "finalizar" -> finalizarCompra(request, response);
		case "confirmacion" -> {
			try {
				confirmacionComprar(request, response);
			} catch (IOException | UsuarioDeslogueadoException e) {
				e.printStackTrace();
			}
		}
		case "mostrarFactura" -> mostrarFactura(request, response);
		case "eliminarRenglon" -> eliminarRenglonDelCarrito(request, response);
		default -> response.sendError(404);
		}
	}

	private void confirmacionComprar(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, UsuarioDeslogueadoException {
		// Obtenemos el carrito actual de sesion
		Carrito carritoActual = obtenerCarritoDeSesion(request);

		// Validamos si el carrito esta vacio
		if (carritoActual.verCarrito().isEmpty()) {
			agregarMensaje(request, "El carrito está vacío. No se puede finalizar la compra.");
			response.sendRedirect("carrito?accion=carrito");
			return;
		}

		// Obtenemos la sesion actual
		HttpSession session = request.getSession();

		// Creamos el decorador
		SessionDecorator sessionDec = new SessionDecorator(session);

		try {

			this.compraRepo = CompraRepoSingleton.getInstance();
			
			// Obtenemos el usuario logeado
			UsuarioBase usuarioLog = sessionDec.getUsuarioLogueado();

			// Se castea a Cliente
			Cliente cliente = (Cliente) usuarioLog;

			// Obtenemos el monto total del carrito
			double montoTotal = carritoActual.verMontoTotal();

			// Obtenemos el saldo del cliente
			double saldoCliente = cliente.getSaldo();

			// Verificar si el saldo es suficiente
			if (montoTotal < saldoCliente) {

				// Si es suficiente descontarlo
				cliente.setSaldo(saldoCliente - montoTotal);

				// Transformar este carrito en una compra
				String nombreCliente = cliente.getNombreUsuario();
				List<Renglon> renglones = carritoActual.verCarrito();
				String numeroFactura = generarNumeroFactura();
				
				request.setAttribute("numeroFactura", numeroFactura);
				
				Compra nuevaCompra = new Compra(nombreCliente, renglones, numeroFactura, montoTotal);
				
				CarritoController.compraRepo.agregarCompra(nuevaCompra);
				
				request.getRequestDispatcher("/views/Carrito/PagoConfirmado.jsp").forward(request, response);

				return;
			} else {
				response.sendRedirect("carrito?error=No tiene saldo suficiente para realizar la compra");
				return;
			}

		} catch (UsuarioDeslogueadoException e) {
			// TODO: handle exception
		}

	}


	private void eliminarRenglonDelCarrito(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String codigoArt = request.getParameter("codigo_art");
		Carrito carritoActual = obtenerCarritoDeSesion(request);

		// Buscar el renglon en el carrito
		Renglon renglonAEliminar = null;
		for (Renglon renglon : carritoActual.verCarrito()) {
			if (renglon.getProducto().getCodigo_art().equals(codigoArt)) {
				renglonAEliminar = renglon;
				break;
			}
		}

		if (renglonAEliminar != null) {
			// Obtener el articulo del renglon
			Articulo articulo = renglonAEliminar.getProducto();
			int cantidad = renglonAEliminar.getCantidad();

			// actualiza el stock del articulo del renglon eliminado
			articulo.setStock(articulo.getStock() + cantidad);

			// Actualizar el artículo en el repositorio
			ArticulosRepoSingleton articuloRepo = ArticulosRepoSingleton.getInstance();
			articuloRepo.updateArticulo(articulo);

			// Eliminar el renglon del carrito
			carritoActual.verCarrito().remove(renglonAEliminar);

			// **Sincronizar el carrito actualizado con la sesión**
			request.getSession().setAttribute("carrito", carritoActual);

			agregarMensaje(request, "El producto fue eliminado del carrito y el stock actualizado.");
		} else {
			agregarMensaje(request, "No se encontró el producto en el carrito.");
		}

		request.getRequestDispatcher("/views/Carrito/carrito.jsp").forward(request, response);

	}

	private void mostrarFactura(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		Carrito carritoActual = (Carrito) session.getAttribute("carrito");

		if (carritoActual.verCarrito().isEmpty()) {
			agregarMensaje(request, "El carrito está vacío. No se puede finalizar la compra.");
			response.sendRedirect("carrito?accion=carrito");
			return;
		}

		

		double total = carritoActual.verMontoTotal();
		List<Renglon> detalleFactura = carritoActual.verCarrito();

		System.out.println(detalleFactura);

		request.getAttribute("numeroFactura");
		request.setAttribute("detalleFactura", detalleFactura);
		request.setAttribute("factura", Map.of("total", total)); // Simula un objeto de factura para JSP

		request.getRequestDispatcher("/views/compras/Factura.jsp").forward(request, response);
		return;
	}

	// Métodos para manejar el carrito
	private void mostrarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Carrito carritoActual = obtenerCarritoDeSesion(request);
		request.setAttribute("carrito", carritoActual);
		request.getRequestDispatcher("/views/Carrito/carrito.jsp").forward(request, response);
	}

	/*
	 * private void mostrarMontoTotal(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * 
	 * Carrito carritoActual = obtenerCarritoDeSesion(request); double montoTotal =
	 * carritoActual.verMontoTotal(); request.setAttribute("montoTotal",
	 * montoTotal);
	 * request.getRequestDispatcher("/views/Carrito/carritoTotal.jsp").forward(
	 * request, response); }
	 */

	private void mostrarVistaAgregar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		articuloRepo = ArticulosRepoSingleton.getInstance();

		// Me traigo la lista de articulos
		List<Articulo> listArt = articuloRepo.getAllArticulos();

		request.setAttribute("listita", listArt);

		request.getRequestDispatcher("/views/Carrito/agregarAlCarrito.jsp").forward(request, response);
	}

	private void finalizarCompra(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtenemos el carrito actual de sesion
		Carrito carritoActual = obtenerCarritoDeSesion(request);

		// Validamos si el carrito esta vacio
		if (carritoActual.verCarrito().isEmpty()) {
			agregarMensaje(request, "El carrito está vacío. No se puede finalizar la compra.");
			response.sendRedirect("carrito?accion=carrito");
			return;
		}

		// Calcular el monto total
		double montoFinal = carritoActual.verMontoTotal();
		for (Renglon renglon : carritoActual.verCarrito()) {
			Articulo articulo = renglon.getProducto();
			articulo.setStock(articulo.getStock() - renglon.getCantidad());
		}

		request.setAttribute("total", montoFinal);

		request.getRequestDispatcher("/views/Carrito/Pago.jsp").forward(request, response);

	}

	// ------------------------ DO POST ------------------------ //
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");

		if ("agregarAlCarrito".equalsIgnoreCase(accion)) {
			agregarProductoAlCarrito(request, response);
		}
	}

	private void agregarProductoAlCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String codigoArt = request.getParameter("codigo_art");
			int cantidad = Integer.parseInt(request.getParameter("cantidad"));

			Articulo articulo = articuloRepo.findArtByCod(codigoArt);
			if (articulo == null || cantidad > articulo.getStock()) {
				agregarMensaje(request, "No se pudo agregar el producto al carrito. Verifica el stock disponible.");
				response.sendRedirect("carrito?accion=agregar");
				return;
			}

			articulo.setStock(articulo.getStock() - cantidad);

			Renglon renglon = new Renglon(cantidad, articulo);

			// Obtengo el carrito actual
			Carrito carritoActual = obtenerCarritoDeSesion(request);

			// Agrego el nuevo renglon al carrito actual
			carritoActual.agregar(renglon);

			response.sendRedirect("carrito?accion=index");
		} catch (Exception e) {
			agregarMensaje(request, "Ocurrió un error al agregar el producto al carrito.");
			response.sendRedirect("carrito?accion=agregar");
		}

	}
}
