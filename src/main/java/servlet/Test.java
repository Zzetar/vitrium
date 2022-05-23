package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import domain.FormaPago;
import domain.Iva;
import domain.Pedido;
import domain.Tarifa;
import exceptions.ServiceException;
import servicios.ServicioCliente;
import servicios.ServicioFormaPago;
import servicios.ServicioIva;
import servicios.ServicioPedido;
import servicios.ServicioTarifa;

/**
 * Servlet implementation class OperacionesCliente
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
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
		PrintWriter out= response.getWriter();
		String salida=null;
		
		Pedido pedido= null;
		ServicioPedido scCliente = new ServicioPedido();
		try {
			pedido=scCliente.recuperarCabeceraPedido(new Pedido(Integer.parseInt(request.getParameter("idPedido"))));
			if(pedido!=null) {
				out.println("<html >");
				out.println("<body>");
				out.println("<p>Pedido n&uacute;mero: "+pedido.getnPed()+"</p>");
				out.println("Fecha: "+pedido.getFechaPed()+"</p>");
				out.println("<button type='button'> <a href='Menu.html'>Volver al Menu</a> </button>");//dentro del servlet si hace falta ponerle en enlace completo con .html
				out.println("</body>");
				out.println("</html >");
			}else {
				salida="/Fin";
				request.setAttribute("mensaje","El pedido indicado no existe");
				getServletContext().getRequestDispatcher(salida).forward(request, response);
			}
		} catch (ServiceException e) {
			e.printStackTrace();// para administrador 
			//Error interno para usuario
			request.setAttribute("mensaje", "error interno");
			salida="/Fin";
			getServletContext().getRequestDispatcher(salida).forward(request, response);
		}
	}

}
