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
import repositories.ClienteRepoSingleton;

@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instancia del repositorio de clientes (Singleton)
    private ClienteRepoSingleton clienteRepo;

    // Constructor del servlet
    public ClienteController() {
        super();
        // Inicializamos el repositorio de clientes
        this.clienteRepo = ClienteRepoSingleton.getInstance();
    }

    // Método que maneja las solicitudes GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos el parámetro 'accion' de la solicitud. Si no está presente, por defecto es "Dashboard".
        String accion = request.getParameter("action");
        
        accion = Optional.ofNullable(accion).orElse("Dashboard");// Si no se especifica acción, se muestra el dashboard

        // Según la acción solicitada, se llama al método correspondiente
        switch (accion) {
            case "Dashboard": // Muestra el dashboard del cliente
                mostrarDashboard(request, response);
                break;
            case "IngresarSaldo": // Muestra el formulario para ingresar saldo
                mostrarIngresarSaldo(request, response);
                break;
            case "RetirarSaldo": // Muestra el formulario para retirar saldo
                mostrarRetirarSaldo(request, response);
                break;
            case "TransferirSaldo": // Muestra el formulario para transferir saldo
                mostrarTransferirSaldo(request, response);
                break;
            default: // Si no se encuentra una acción válida, se responde con error 404
                response.sendError(404);
        }
    }

    // Método que maneja las solicitudes POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos el parámetro 'accion' de la solicitud, con un valor por defecto "Dashboard"
        String accion = request.getParameter("accion");
        accion = (accion != null) ? accion : "Dashboard";

        // Según la acción solicitada, se llama al método correspondiente
        switch (accion) {
            case "IngresarSaldo": // Llama al método para ingresar saldo
                ingresarSaldo(request, response);
                break;
            case "RetirarSaldo": // Llama al método para retirar saldo
                retirarSaldo(request, response);
                break;
            case "TransferirSaldo": // Llama al método para transferir saldo
                transferirSaldo(request, response);
                break;
            default: // Si no se encuentra una acción válida, se responde con error 404
                response.sendError(404);
        }
    }

    // Método para mostrar el dashboard del cliente (incluso su saldo y nombre)
    private void mostrarDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos la sesión del usuario logueado
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("usuarioLoggeado");

        // Si el cliente está logueado, mostramos su dashboard
        if (cliente != null) {
            request.setAttribute("cliente", cliente); // Pasamos el cliente a la vista
            request.getRequestDispatcher("clienteDashboard.jsp").forward(request, response); // Redirige a la vista del dashboard
        } else {
            // Si no hay cliente logueado, redirige al formulario de login
            response.sendRedirect("Login?accion=Login");
        }
    }

 // Método para mostrar el formulario de ingreso de saldo
    private void mostrarIngresarSaldo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/usuario/ingresarSaldo.jsp").forward(request, response);
    }

    // Método para mostrar el formulario de retiro de saldo
    private void mostrarRetirarSaldo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/usuario/retirarSaldo.jsp").forward(request, response);
    }

    // Método para mostrar el formulario de transferencia de saldo
    private void mostrarTransferirSaldo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/usuario/transferirSaldo.jsp").forward(request, response);
    }


    // Funcionalidad para ingresar saldo en la cuenta del cliente
    private void ingresarSaldo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos la sesión del cliente
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("usuarioLoggeado");

        // Verificamos si el cliente está logueado
        if (cliente != null) {
            // Obtenemos el saldo a ingresar desde el formulario
            double saldo = Double.parseDouble(request.getParameter("saldo"));
            // Llamamos al método de la clase Cliente para ingresar el saldo
            cliente.ingresarSaldo(saldo);
            // Mostramos un mensaje de éxito y redirigimos al dashboard
            request.setAttribute("mensaje", "Saldo ingresado correctamente.");
            mostrarDashboard(request, response);
        } else {
            // Si no hay cliente logueado, redirigimos al login
            response.sendRedirect("LoginController?action=Log-in");
        }
    }

    // Funcionalidad para retirar saldo de la cuenta del cliente
    private void retirarSaldo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos la sesión del cliente
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("usuarioLoggeado");

        // Verificamos si el cliente está logueado
        if (cliente != null) {
            // Obtenemos el saldo a retirar desde el formulario
            double saldo = Double.parseDouble(request.getParameter("saldo"));
            // Verificamos si el cliente tiene suficiente saldo para realizar el retiro
            if (cliente.retirarSaldo(saldo)) {
                // Mostramos un mensaje de éxito
                request.setAttribute("mensaje", "Saldo retirado correctamente.");
            } else {
                // Si el cliente no tiene suficiente saldo, mostramos un mensaje de error
                request.setAttribute("mensaje", "No tiene suficiente saldo.");
            }
            mostrarDashboard(request, response);
        } else {
            // Si no hay cliente logueado, redirigimos al login
            response.sendRedirect("LoginController?action=Log-in");
        }
    }

    // Funcionalidad para transferir saldo a otro cliente
    private void transferirSaldo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos la sesión del cliente
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("usuarioLoggeado");

        // Verificamos si el cliente está logueado
        if (cliente != null) {
            // Obtenemos el nombre de usuario del destinatario y el monto a transferir desde el formulario
            String nombreUsuarioDestino = request.getParameter("usuarioDestino");
            double monto = Double.parseDouble(request.getParameter("monto"));
            // Buscamos al cliente destinatario en el repositorio
            Cliente clienteDestino = clienteRepo.getCliente(nombreUsuarioDestino);
            // Si el cliente destino existe y la transferencia es exitosa, actualizamos ambos clientes en el repositorio
            if (clienteDestino != null && cliente.transferirSaldo(clienteDestino, monto)) {
                // Mostramos un mensaje de éxito
                request.setAttribute("mensaje", "Transferencia realizada correctamente.");
            } else {
                // Si no se pudo realizar la transferencia, mostramos un mensaje de error
                request.setAttribute("mensaje", "No se pudo realizar la transferencia.");
            }
            mostrarDashboard(request, response);
        } else {
            // Si no hay cliente logueado, redirigimos al login
            response.sendRedirect("LoginController?action=Log-in");
        }
    }
}
