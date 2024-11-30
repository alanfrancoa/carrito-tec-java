package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.compras.Compra;
import repositories.interfaces.CompraRepo;


@WebServlet("/CompraController")
public class CompraController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CompraRepo compraRepo;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "verHistorialCliente":
                verHistorialCliente(request, response);
                break;
            case "verTodasLasCompras":
                verTodasLasCompras(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void verHistorialCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreCliente = request.getParameter("nombreCliente");
        List<Compra> comprasCliente = compraRepo.obtenerComprasPorCliente(nombreCliente);

        request.setAttribute("comprasCliente", comprasCliente);
        request.setAttribute("nombreCliente", nombreCliente);
        request.getRequestDispatcher("views/compras/historialCliente.jsp").forward(request, response);
    }

    private void verTodasLasCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Compra> todasLasCompras = compraRepo.obtenerTodasLasCompras();

        request.setAttribute("todasLasCompras", todasLasCompras);
        request.getRequestDispatcher("views/compras/todasLasCompras.jsp").forward(request, response);
    }
}

