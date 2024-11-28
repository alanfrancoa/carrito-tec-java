package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.articulos.Articulo;
import modelos.carrito.Carrito;
import modelos.carrito.Renglon;
import repositories.CarritoRepoSingleton;
import repositories.interfaces.CarritoRepo;

@WebServlet("/CarritoController")
public class CarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ------------------------ Metodos del Servlet ------------------------ //
	
	// Obtener o crear el carrito actual de sesion
    private Carrito obtenerCarritoDeSesion(HttpServletRequest request) {
    	
    	// Obtenemos la sesion actual
        HttpSession session = request.getSession();
        
        // Obtenemos el carrito actual de la sesion iniciada
        Carrito carritoActual = (Carrito) session.getAttribute("carrito");
        
        // Validamos la existencia del carrito
        if (carritoActual == null) {
        	
        	// Obtenemos la instancia unica (Singleton)
            carritoActual = Carrito.getInstance();
            
            // Le pasamos el carrito actual a la sesion mediante el atributo "carrito"
            session.setAttribute("carrito", carritoActual);
        }
        return carritoActual;
    }

	// ------------------------ DO GET ------------------------ //
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recibo un parametro del request
		String accion = request.getParameter("accion");

		// Usamos un optional en caso que no se pase nada por parametro
		accion = Optional.ofNullable(accion).orElse("index");

		// Hacemos un switch para las distintas acciones
		switch (accion) {
		case "index" -> mostrarCarrito(request, response);
		case "total" -> mostrarMontoTotal(request, response);
		case "finalizar" -> finalizarCompra(request, response);
		default -> response.sendError(404);
		}
	}

	// ------ METODOS DO GET------ //

	// Mostrar el contenido del carrito
	private void mostrarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtenemos el carrito de la sesion actual
		Carrito carritoActual = obtenerCarritoDeSesion(request);
		request.setAttribute("carrito", carritoActual);
		request.getRequestDispatcher("views/carrito/carrito.jsp").forward(request, response);

	}

	// Mostrar el monto total de carrito
	private void mostrarMontoTotal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtenemos el carrito de la sesion actual
		Carrito carritoActual = obtenerCarritoDeSesion(request);
		
		// Obtenemos el monto total del carrito actual
		double montoTotal = carritoActual.verMontoTotal();
		request.setAttribute("montoTotal", montoTotal);
		request.getRequestDispatcher("views/carrito/carritoTotal.jsp").forward(request, response);

	}

	// FIanlizar la compra del carrito
	private void finalizarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Obtenemos el carrito de la sesion actual
		Carrito carritoActual = obtenerCarritoDeSesion(request);
		
		// Obtenemos el monto total del carrito actual
		double montoFinal = carritoActual.verMontoTotal();
		
		// Recorremos el carrito
		for(Renglon renglon : carritoActual.verCarrito()) {
			
			// Por cada renglon del carrito obtenemos el articulo
			Articulo articulo = renglon.getProducto();
			
			// Modificamos el stock del articulo una vez hecha la compra
			articulo.setStock(articulo.getStock() - renglon.getCantidad());
		}
		
		// Finalizamos la compra del carrito actual
		carritoActual.finalizarCompra();
		
		request.setAttribute("montoFinal", montoFinal);
        request.getRequestDispatcher("views/carrito/finalizar.jsp").forward(request, response);
	}

	// ------------------------ DO POST ------------------------ //
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recibo un parametro del request
		String accion = request.getParameter("accion");
		
		// Si la accion es igual al parametro entonces realizo la accion de POST
		if("agregar".equalsIgnoreCase(accion)) {
			agregarProductoAlCarrito(request,response);
		}
	}

	// ------ METODOS DO GET------ //
	private void agregarProductoAlCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Obtenemos los datos de la vista 
		int articuloId = Integer.parseInt(request.getParameter("articuloid"));
	
		// Obtenemos el carrito de la sesion actual
		Carrito carritoActual = obtenerCarritoDeSesion(request);
		
		// Obtenemos los datos del parametro
        int idArticulo = Integer.parseInt(request.getParameter("id_articulo"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String rubro = request.getParameter("rubro");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        // Ver si hay stock de dicho producto
        if (cantidad > stock) {
            request.setAttribute("error", "Stock insuficiente para el artículo: " + nombre);
            request.getRequestDispatcher("views/carrito/error.jsp").forward(request, response);
            return;
        }

        // Crear el artículo y el renglón
        Articulo articulo = new Articulo(idArticulo, nombre, precio, stock, rubro);
        Renglon renglon = new Renglon(cantidad, articulo);
        
        // Agregamos al carrito actual el renglon
        carritoActual.agregar(renglon);
        
        // Nos redirijimos al index de ver carrito
        response.sendRedirect("CarritoController?accion=index");
        
	}

}
