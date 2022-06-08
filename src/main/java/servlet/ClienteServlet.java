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

/**
 * Servlet implementation class OperacionesCliente
 */
@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteServlet() {
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
	
		ServicioCliente sCliente = new ServicioCliente();
		try {
			Cliente cliente= clienteDePeticion(request);
			sCliente.insertarCliente(cliente);
			request.getSession().setAttribute("cliente", cliente);
			
			salida="/ProductosDisponibles.jsp";
			request.setAttribute("info","Cliente dado de alta correctamente");
			
		} catch (ServiceException e) {
			e.printStackTrace();// para administrador 
			//Error interno para usuario
			request.setAttribute("mensaje", "error interno");
			salida="/Fin";
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

	private Cliente clienteDePeticion(HttpServletRequest request) {
		Cliente cliente= new Cliente();
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellido1(request.getParameter("apellido1"));
		cliente.setApellido2(request.getParameter("apellido2"));
		cliente.setProvincia(request.getParameter("provincia"));
		cliente.setLocalidad(request.getParameter("localidad1"));
		cliente.setDireccion(request.getParameter("direccion"));
		cliente.setCodigoPostal(Integer.parseInt(request.getParameter("cp")));
		cliente.setEmail(request.getParameter("email"));
		cliente.setPassword(request.getParameter("password"));
		cliente.setClase(Integer.parseInt(request.getParameter("clase")));

		return cliente;
	}

}
