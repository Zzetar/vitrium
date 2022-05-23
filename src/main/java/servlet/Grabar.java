package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import domain.Iva;
import domain.Tarifa;
import domain.FormaPago;
import exceptions.ServiceException;
import servicios.ServicioCliente;

/**
 * Servlet implementation class Grabar
 */
@WebServlet("/Grabar")
public class Grabar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String Codigo=null;
	String RazonSocial=null;
	String Telefono=null;
	String Direccion=null;
	String Oferta=null;
	String Albaran =null;
	String CodigoIva=null;
	String CodigoTarifa=null;
	String FormaPago=null;

	String Salida=null;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Grabar() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();

		Codigo=request.getParameter("inputCodCliente");
		RazonSocial=request.getParameter("inputRazonSocial");
		Telefono=request.getParameter("inputTelefono");
		Direccion=request.getParameter("inputDireccion");
		Oferta=request.getParameter("inputOferta");
		Albaran =request.getParameter("inputAlbFactura");

		CodigoIva=request.getParameter("inputIva");

		CodigoTarifa=request.getParameter("inputTarifa");

		FormaPago=request.getParameter("inputFormaPago");

		if(Codigo=="") {
			request.setAttribute("mensajeError", "Se debe rellenar el codigo");
			Salida=("/InsertarCliente2");
			getServletContext().getRequestDispatcher(Salida).forward(request, response);

		}
		if(RazonSocial=="") {
			request.setAttribute("mensajeError", "Se debe rellenar la RazonSocial");
			Salida=("/InsertarCliente2");
			getServletContext().getRequestDispatcher(Salida).forward(request, response);

		}
		if(Telefono=="") {
			request.setAttribute("mensajeError", "Se debe rellenar el Telefono");
			Salida=("/InsertarCliente2");
			getServletContext().getRequestDispatcher(Salida).forward(request, response);

		}
		if(Direccion=="") {
			request.setAttribute("mensajeError", "Se debe rellenar la Direccion");
			Salida=("/InsertarCliente2");
			getServletContext().getRequestDispatcher(Salida).forward(request, response);

		}




		Iva i= new Iva();
		i.setcodIva(CodigoIva);
		Tarifa t = new Tarifa();
		t.setcodTarifa(CodigoTarifa);
		FormaPago fp =new FormaPago();
		fp.setCodigo(FormaPago);

		ServicioCliente scCliente = new ServicioCliente();
		Cliente cl= new Cliente(Codigo, RazonSocial, Telefono, Direccion, Oferta, Albaran, i, t,fp); //otra forma 

		try {

			//scCliente.insertarCliente(Codigo, RazonSocial, Telefono, Direccion, Oferta, Albaran, CodigoIva, CodigoTarifa, FormaPago);	
			//Primero recuperamos el cliente para ver si existe y si existe sacamos el error
			if(scCliente.recuperarClienteCompletoById(cl.getCodCli())!=null) {
				Salida=("/Fin");
				request.setAttribute("mensaje", "Cliente ya existe");
				getServletContext().getRequestDispatcher(Salida).forward(request, response);
			}
			scCliente.insertarCliente(cl); //la otra forma
			
			
			Salida=("/Fin");
			request.setAttribute("mensaje", "Cliente grabado correctamente");
			getServletContext().getRequestDispatcher(Salida).forward(request, response);


		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			Salida=("/Fin");
			request.setAttribute("mensaje", "Error al insertar ");//como llevar los errores por duplicado o exceso de tamaño escrito¿?¿
			getServletContext().getRequestDispatcher(Salida).forward(request, response);
		}


		out.close();
	}

}
