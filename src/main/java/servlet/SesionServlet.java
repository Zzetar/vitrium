package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import exceptions.ServiceException;
import servicios.ServicioCliente;

@WebServlet("/iniciarSesion")
public class SesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 1024*10;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String salida= null;
	
		ServicioCliente sCliente = new ServicioCliente();
		try {
			Cliente cliente= sCliente.recuperarClienteByEmail(request.getParameter("email"));
			if (cliente != null) {
				if (cliente.getPassword() != null && cliente.getPassword().equals(request.getParameter("password"))) {
					request.getSession().setAttribute("cliente", cliente);
					
					salida="/productosDisponibles.jsp";
				} else {
					request.setAttribute("error", "Contraseña incorrecta");
					salida="/iniciarSesion.jsp";
				}
			} else {
				request.setAttribute("error", "El cliente no existe");
				salida="/iniciarSesion.jsp";
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();// para administrador 
			//Error interno para usuario
			request.setAttribute("mensaje", "error interno");
			salida="/Fin";
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}
}
