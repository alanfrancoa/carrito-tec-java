package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.compras.Compra;
import repositories.CompraRepoSingleton;
import repositories.interfaces.CompraRepo;

/**
 * Controlador para gestionar las acciones de las compras.
 * Mapea las peticiones enviadas a "/CompraController" y delega la lógica
 */
@WebServlet("/CompraController")
public class CompraController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Repositorio que gestiona las operaciones de almacenamiento de compras
    private CompraRepo compraRepo;

    /**
     * Constructor del controlador.
     */
    public CompraController() {
        super();
        this.compraRepo = CompraRepoSingleton.getInstance(); // Obtener la instancia del repositorio (Singleton)
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro "accion" de la solicitud
        String accion = request.getParameter("accion");

        // Verificar la acción solicitada y delegar al método correspondiente
        switch (accion) {
            case "verHistorialCliente":
                verHistorialCliente(request, response);
                break;
            case "verTodasLasCompras":
                verTodasLasCompras(request, response);
                break;
            default:
                // Si la acción no es válida, enviar un error 404
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Acción no válida.");
        }
    }

    /**
     * Muestra el historial de compras realizadas por un cliente específico.
     *
     * @param request  Objeto que contiene los datos de la solicitud HTTP.
     * @param response Objeto que se utiliza para enviar la respuesta HTTP.
     */
    private void verHistorialCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el nombre del cliente desde los parámetros de la solicitud
        String nombreCliente = request.getParameter("nombreCliente");

        // Recuperar las compras del cliente desde el repositorio
        List<Compra> comprasCliente = compraRepo.obtenerComprasPorCliente(nombreCliente);

        // Pasar los datos a la vista utilizando atributos del request
        request.setAttribute("comprasCliente", comprasCliente);
        request.setAttribute("nombreCliente", nombreCliente);

        // Redirigir a la vista JSP que muestra el historial del cliente
        request.getRequestDispatcher("views/compras/HistorialComprasCliente.jsp").forward(request, response);
    }

    /**
     * Muestra todas las compras realizadas por todos los clientes.
     *
     * @param request  Objeto que contiene los datos de la solicitud HTTP.
     * @param response Objeto que se utiliza para enviar la respuesta HTTP.
     */
    private void verTodasLasCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar todas las compras desde el repositorio
        List<Compra> todasLasCompras = compraRepo.obtenerTodasLasCompras();

        // Pasar los datos a la vista utilizando atributos del request
        request.setAttribute("todasLasCompras", todasLasCompras);

        // Redirigir a la vista JSP que muestra todas las compras
        request.getRequestDispatcher("views/compras/HistorialComprasTotal.jsp").forward(request, response);
    }
}
