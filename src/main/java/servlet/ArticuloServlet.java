package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Articulo;
import domain.Cliente;
import exceptions.ServiceException;
import servicios.ServicioArticulo;
import servicios.ServicioCliente;

/**
 * Servlet implementation class OperacionesCliente
 */
@WebServlet("/Articulo")
public class ArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticuloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String salida= null;
	
		ServicioArticulo sArticulo = new ServicioArticulo();
		try {
			Articulo articulo= articuloDePeticion(request);
			sArticulo.insertarArticulo(articulo);
			request.getSession().setAttribute("articulo", articulo);
			
			salida="/ProductosDisponibles.jsp";
			request.setAttribute("info","Articulo dado de alta correctamente");
			
		} catch (ServiceException e) {
			e.printStackTrace();// para administrador 
			//Error interno para usuario
			request.setAttribute("mensaje", "error interno");
			salida="/Fin";
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

	private Articulo articuloDePeticion(HttpServletRequest request) {
		Articulo articulo= new Articulo();
		articulo.setCategoria(request.getParameter("categoria"));
		articulo.setPrecio(Integer.parseInt(request.getParameter("precio")));
		articulo.setDescripcion(request.getParameter("descripcion"));
		articulo.setPath(request.getParameter("path"));

		return articulo;
	}

}
