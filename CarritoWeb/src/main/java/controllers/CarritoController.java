package controllers;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/CarritoController")
public class CarritoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

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

    // ------------------------ DO GET ------------------------ //
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("index");

        switch (accion) {
            case "index" -> mostrarCarrito(request, response);
            case "total" -> mostrarMontoTotal(request, response);
            case "finalizar" -> finalizarCompra(request, response);
            default -> response.sendError(404);
        }
    }

    // Métodos para manejar el carrito
    private void mostrarCarrito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Carrito carritoActual = obtenerCarritoDeSesion(request);
        request.setAttribute("carrito", carritoActual);
        request.getRequestDispatcher("views/carrito/carrito.jsp").forward(request, response);
    }

    private void mostrarMontoTotal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Carrito carritoActual = obtenerCarritoDeSesion(request);
        double montoTotal = carritoActual.verMontoTotal();
        request.setAttribute("montoTotal", montoTotal);
        request.getRequestDispatcher("views/carrito/carritoTotal.jsp").forward(request, response);
    }

    private void finalizarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Carrito carritoActual = obtenerCarritoDeSesion(request);

        if (carritoActual.verCarrito().isEmpty()) {
            agregarMensaje(request, "El carrito está vacío. No se puede finalizar la compra.");
            response.sendRedirect("CarritoController?accion=index");
            return;
        }
        
        
        double montoFinal = carritoActual.verMontoTotal();
        for (Renglon renglon : carritoActual.verCarrito()) {
            Articulo articulo = renglon.getProducto();
            articulo.setStock(articulo.getStock() - renglon.getCantidad());
        }
        
        carritoActual.finalizarCompra();
        agregarMensaje(request, "Compra finalizada con éxito. Monto total: " + montoFinal);
        response.sendRedirect("CarritoController?accion=index");
    }

    // ------------------------ DO POST ------------------------ //
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("agregar".equalsIgnoreCase(accion)) {
            agregarProductoAlCarrito(request, response);
        }
    }

    private void agregarProductoAlCarrito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int stock = Integer.parseInt(request.getParameter("stock"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        if (cantidad > stock) {
            agregarMensaje(request, "Stock insuficiente para el artículo.");
            response.sendRedirect("CarritoController?accion=index");
            return;
        }

        String codigoArt = request.getParameter("codigo-articulo");
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String rubro = request.getParameter("rubro");

        Articulo articulo = new Articulo(codigoArt, nombre, precio, stock, rubro);
        Renglon renglon = new Renglon(cantidad, articulo);

        Carrito carritoActual = obtenerCarritoDeSesion(request);
        carritoActual.agregar(renglon);

        agregarMensaje(request, "Producto agregado al carrito exitosamente.");
        response.sendRedirect("CarritoController?accion=index");
    }
}
