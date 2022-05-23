package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import servicios.ServicioCliente;

/**
 * Servlet implementation class BorrarCliente
 */
@WebServlet("/BorrarCliente")
public class BorrarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		
		//borramos el cliente recogiendo el cod cliente que hemos seleccionado
		//recuperamos el codigo cliente
		String numero= request.getParameter("inputCodCliente");
		String Salida=null;
		
		out.println(numero +" codigo");
		ServicioCliente scCliente =new ServicioCliente();
		Cliente cliente=null;
		int borrado=0;
		try {
			cliente=new Cliente();
			cliente.setCodCli(numero);
			
			borrado=scCliente.borrarCliente(cliente);
			
			if(borrado==1) {
				request.setAttribute("mensaje", "El cliente ha sido borrado con exito!");
				Salida=("/Fin");
				getServletContext().getRequestDispatcher(Salida).forward(request, response);
			}else {
				request.setAttribute("mensaje", "No hay cliente que borrar");
				Salida=("/Fin");
				getServletContext().getRequestDispatcher(Salida).forward(request, response);
			}
			
		} catch (Exception e) {
			//e.printStackTrace();// para administrador 
			//Error interno para usuario
			request.setAttribute("mensaje", "error interno");
			Salida=("/Fin");
			getServletContext().getRequestDispatcher(Salida).forward(request, response);
		}
	}

}
