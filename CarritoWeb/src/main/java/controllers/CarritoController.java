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

import modelos.articulos.Articulo;
import modelos.carrito.Carrito;
import modelos.carrito.Renglon;
import modelos.compras.Compra;
import repositories.ArticulosRepoSingleton;
import repositories.CompraRepoSingleton;
import repositories.interfaces.CompraRepo;

@WebServlet("/carrito")
public class CarritoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static ArticulosRepoSingleton articuloRepo;

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
            case "total" -> mostrarMontoTotal(request, response);
            case "agregar" -> mostrarVistaAgregar(request, response);
            case "finalizar" -> finalizarCompra(request, response);
            case "verFactura" -> mostrarFactura(request, response); 
            default -> response.sendError(404);
        }
    }

	private void mostrarFactura(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 HttpSession session = request.getSession();
		    Carrito carritoActual = (Carrito) session.getAttribute("carrito");

		    if (carritoActual.verCarrito().isEmpty()) {
		    	 agregarMensaje(request, "El carrito está vacío. No se puede finalizar la compra.");
		            response.sendRedirect("carrito?accion=carrito");
		            return;
		    }

		    String numeroFactura = generarNumeroFactura();

		    double total = carritoActual.verMontoTotal();
		    List<Renglon> detalleFactura = carritoActual.verCarrito();

		    request.setAttribute("numeroFactura", numeroFactura);
		    request.setAttribute("detalleFactura", detalleFactura);
		    request.setAttribute("factura", Map.of("total", total)); // Simula un objeto de factura para JSP

		    request.getRequestDispatcher("/views/carrito/Factura.jsp").forward(request, response);
		    return;
	}

	// Métodos para manejar el carrito
    private void mostrarCarrito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Carrito carritoActual = obtenerCarritoDeSesion(request);
        request.setAttribute("carrito", carritoActual);
        request.getRequestDispatcher("/views/carrito/carrito.jsp").forward(request, response);
    }

    private void mostrarMontoTotal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Carrito carritoActual = obtenerCarritoDeSesion(request);
        double montoTotal = carritoActual.verMontoTotal();
        request.setAttribute("montoTotal", montoTotal);
        request.getRequestDispatcher("/views/carrito/carritoTotal.jsp").forward(request, response);
    }
    
    private void mostrarVistaAgregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	articuloRepo = ArticulosRepoSingleton.getInstance();
    	
    	// Me traigo la lista de articulos
    	List<Articulo> listArt = articuloRepo.getAllArticulos();
    	
		request.setAttribute("listita", listArt);
		
    	request.getRequestDispatcher("/views/carrito/agregarAlCarrito.jsp").forward(request, response);
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
                       
        request.getRequestDispatcher("/views/carrito/Pago.jsp").forward(request, response);
       
        
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
