package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.articulos.Articulo;
import repositories.ArticulosRepoSingleton;
import repositories.interfaces.ArticuloRepo;


@WebServlet("/articulos")
public class ArticuloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private ArticuloRepo articulosRepo;
		
	
    public ArticuloController() {
        super();
        this.articulosRepo = ArticulosRepoSingleton.getInstance();
       
    }

    /*----------------------DO GET--------------------------*/
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("index");
		
		switch (accion) {
		case "index" -> getIndex(request,response);
		case "MostrarArticulos" -> getMostrarArticulos(request,response);
		case "EditarArticulos" -> getEditarArticulos(request,response);
		case "CrearArticulos" -> getCrearArticulos(request,response);
				
		default-> response.sendError(404);
			
		}
	}

	
	private void getCrearArticulos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/articulos/CrearArticulos.jsp").forward(request, response);
	}

	private void getEditarArticulos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sCodigo = request.getParameter("codigo_art");
		
		
		ArticuloRepo repo = ArticulosRepoSingleton.getInstance();
		
		Articulo arti= repo.findArtByCod(sCodigo);
		
		request.setAttribute("articulo", arti);
		
		request.getRequestDispatcher("/views/articulos/EditarArticulos.jsp").forward(request, response);
		
		
	}

	private void getMostrarArticulos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sCodigo = request.getParameter("codigo_art");
				
		Articulo arti = articulosRepo.findArtByCod(sCodigo);
		request.setAttribute("articulo", arti);
		
		request.getRequestDispatcher("/views/articulos/MostrarArticulos.jsp").forward(request, response);
		
	}


	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArticuloRepo repo = ArticulosRepoSingleton.getInstance();
		
		 List<Articulo> listArt = repo.getAllArticulos();
		 request.setAttribute("listita", listArt);		
		
		request.getRequestDispatcher("/views/articulos/index.jsp").forward(request, response);

	}

	

    /*----------------------DO POST--------------------------*/
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		//accion = Optional.ofNullable(accion).orElse("CrearArticulos");
		
		if(accion== null) {
			
			response.sendError(400, "no se brindo una accion");
			//cortar el metodo y asegurar que el codigo no avance//
			return;
		}

		switch (accion) {
		case "CrearArticulos" -> postCrearArticulos(request,response);
		case "EditarArticulos" -> postEditarArticulos(request,response);
		case "EliminarArticulos" -> postEliminarArticulos(request,response);
		
						
		default-> response.sendError(404,"No existe la accion: " +accion);
			
		}
		
		
	}

	private void postEliminarArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String codigo = request.getParameter("codigo");
		
		articulosRepo.deleteArticulo(codigo);
		
		response.sendRedirect("articulos");
		
		
		
	}

	private void postEditarArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		String codigo = request.getParameter("codigo");
		
		String nombre = request.getParameter("nombre");
		
		String sPrecio = request.getParameter("precio");
		double precio = Double.parseDouble(sPrecio);
		
		String sStock = request.getParameter("stock");
		int stock = Integer.parseInt(sStock);
		
		String rubro = request.getParameter("rubro");
		
		
		Articulo arti = articulosRepo.findArtByCod(codigo);
		arti.setNombre(nombre);
		arti.setPrecio(precio);
		arti.setStock(stock);
		arti.setRubro(rubro);
		
		articulosRepo.updateArticulo(arti);
		
		response.sendRedirect("articulos");
	}

	private void postCrearArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String codigo = request.getParameter("codigo");
		
		String nombre = request.getParameter("nombre");
		
		String sPrecio = request.getParameter("precio");
		double precio = Double.parseDouble(sPrecio);
		
		String sStock = request.getParameter("stock");
		int stock = Integer.parseInt(sStock);
		
		String rubro = request.getParameter("rubro");
		
		
		Articulo arti = new Articulo(codigo, nombre, precio, stock, rubro);
		
		articulosRepo.createArticulo(arti);
		
		response.sendRedirect("articulos");
		
	}

}
