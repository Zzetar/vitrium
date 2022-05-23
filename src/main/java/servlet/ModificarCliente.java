package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import domain.FormaPago;
import domain.Iva;
import domain.Tarifa;
import servicios.ServicioCliente;

/**
 * Servlet implementation class ModificarCliente
 */
@WebServlet("/ModificarCliente")
public class ModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarCliente() {
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
		ServicioCliente scCliente= new ServicioCliente();
		Cliente clienteActual=null;
		Cliente clienteModificado=null;
		Iva iva= null;
		Tarifa tarifa=null;
		FormaPago formaPago=null;
		
			try {
				iva=new Iva();
				iva.setcodIva(request.getParameter("inputIva"));
				tarifa= new Tarifa();
				tarifa.setcodTarifa( request.getParameter("inputTarifa"));
				formaPago = new FormaPago();
				formaPago.setCodigo(request.getParameter("inputFormaPago"));
				
				clienteActual=scCliente.recuperarClienteCompletoById(request.getParameter("inputCodCliente"));
				clienteModificado=new Cliente(
						request.getParameter("inputCodCliente"),
						request.getParameter("inputRazonSocial"),
						request.getParameter("inputTelefono"),
						request.getParameter("inputDireccion"),
						request.getParameter("inputOferta"),
						request.getParameter("inputAlbFactura"),
						iva,
						tarifa,
						formaPago);
				out.println(clienteActual.getRazonSocial()+ " Viejo");
				out.println(clienteModificado.getRazonSocial()+ " Nuevo");
				try {
					scCliente.modificarClienteConcurrente(clienteModificado	, clienteActual);
					request.setAttribute("mensaje", "El cliente  ha sido modificado correctamente");
					salida=("/Fin");
					getServletContext().getRequestDispatcher(salida).forward(request, response);
					
				} catch (Exception e) {

					
					e.printStackTrace();// para administrador 
					//Error interno para usuario
					request.setAttribute("mensaje", "El cliente ya ha sido modificado por otra persona");
					salida=("/Fin");
					getServletContext().getRequestDispatcher(salida).forward(request, response);
					

				}
//				scCliente.modificarClienteConcurrente(cliente, cliente)
			} catch (Exception e) {

				
				e.printStackTrace();// para administrador 
				//Error interno para usuario
				request.setAttribute("mensaje", "error interno");
				salida=("/Fin");
				getServletContext().getRequestDispatcher(salida).forward(request, response);
				

			}

	}

}
