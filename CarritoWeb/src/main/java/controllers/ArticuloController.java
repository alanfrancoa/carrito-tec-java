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
		accion = Optional.ofNullable(accion).orElse("Index");
		
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
		
		List<Articulo> listArti = repo.getAllArticulos();
		
		request.setAttribute("listita",listArti);
		
		request.getRequestDispatcher("/views/articulos/index.jsp").forward(request, response);

	}

	

    /*----------------------DO POST--------------------------*/
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(400);
	}

}
