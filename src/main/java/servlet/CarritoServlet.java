package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Articulo;
import domain.Carrito;
import domain.Cliente;
import exceptions.ServiceException;
import servicios.ServicioCliente;

/**
 * Servlet implementation class OperacionesCliente
 */
@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String salida= null;
	
		Carrito carrito= (Carrito) request.getSession().getAttribute("carrito");
		if (carrito== null) {
			carrito= new Carrito();
			request.getSession().setAttribute("carrito", carrito);
		}
		Articulo articulo= new Articulo();
		articulo.setIdArticulo(Integer.parseInt(request.getParameter("idArticulo")));
		articulo.setPrecio(Integer.parseInt(request.getParameter("precio")));
		
		carrito.addArticulo(articulo, Integer.parseInt(request.getParameter("cantidad")));
		
		salida="/productosDisponibles.jsp";
		
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

}
