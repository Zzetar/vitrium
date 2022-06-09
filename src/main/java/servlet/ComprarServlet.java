package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.Articulo;
import domain.Carrito;
import domain.Cliente;
import domain.Factura;
import domain.Pedido;
import exceptions.ServiceException;
import servicios.ServicioArticulo;
import servicios.ServicioPedido;

/**
 * Servlet implementation class OperacionesCliente
 */
@WebServlet("/comprar")
public class ComprarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 1024*10;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarServlet() {
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
		
		Cliente cliente= (Cliente) request.getSession().getAttribute("cliente");
		Carrito carrito= (Carrito) request.getSession().getAttribute("carrito");
	
		if (cliente == null) {
			salida="/iniciarSesion.jsp";
		} else if (carrito == null || carrito.isVacio()) {
			request.setAttribute("mensaje", "El carrito está vacío");
			salida="/Fin";
		} else {
			ServicioPedido sPedido= new ServicioPedido();
			
			Pedido pedido= new Pedido();
			pedido.setIdCliente(cliente.getIdCliente());
			pedido.setEstadoPedido("En proceso");
			pedido.setFechaPed(new Date(new java.util.Date().getTime()));
			pedido.setImporte(carrito.precioTotal());
			try {
				sPedido.insertarPedidoCompleto(pedido, carrito.getLineasPedido());
				request.getSession().removeAttribute("carrito");

				request.setAttribute("lineas", carrito.getLineasPedido());
				request.setAttribute("pedido", pedido);
				salida="/compraFinalizada.jsp";
			} catch (ServiceException e) {
				e.printStackTrace();// para administrador 
				//Error interno para usuario
				request.setAttribute("mensaje", "error interno");
				salida="/Fin";
			}
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

}
