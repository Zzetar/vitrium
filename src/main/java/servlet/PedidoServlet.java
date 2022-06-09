package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import domain.Pedido;
import exceptions.ServiceException;
import servicios.ServicioPedido;

@WebServlet("/pedidos")
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 1024*10;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String salida= null;
		Cliente cliente= (Cliente) request.getSession().getAttribute("cliente");
		if (cliente == null) {
			salida="/iniciarSesion.jsp";
		} else {
			try {
				ServicioPedido sPedido = new ServicioPedido();
				List<Pedido> pedidosCliente= sPedido.recuperarPedidosCliente(cliente.getIdCliente());
				request.setAttribute("pedidos", pedidosCliente);
				salida="/pedidos.jsp";
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
