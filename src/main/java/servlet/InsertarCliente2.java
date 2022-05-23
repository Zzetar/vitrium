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

import domain.FormaPago;
import domain.Iva;
import domain.Tarifa;
import exceptions.ServiceException;
import servicios.ServicioFormaPago;
import servicios.ServicioIva;
import servicios.ServicioTarifa;

/**
 * Servlet implementation class InsertarCliente2
 */
@WebServlet("/InsertarCliente2")
public class InsertarCliente2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertarCliente2() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		// TODO Auto-generated method stub

		String salida=null;
		ServicioIva scIva=null;
		List<Iva> listaIva= new ArrayList<Iva>();

		ServicioTarifa scTarifas= null;
		List<Tarifa> listaTarifas = new ArrayList<Tarifa>();

		ServicioFormaPago scFormaPago=null;
		List<FormaPago> listaFormaPago= new ArrayList<FormaPago>();

		try{
			salida="/fin";
			//Iva
			scIva = new ServicioIva();
			listaIva = scIva.recuperarTodosIvas();
			request.setAttribute("listaIva", listaIva); 
			//List<Iva> listaIvasRec= (List<Iva>) request.getAttribute("listaIva");

			//Tarifa
			scTarifas = new ServicioTarifa();
			listaTarifas = scTarifas.RecuperarTodasTarifas();
			request.setAttribute("listaTarifas", listaTarifas);
			//List<Tarifa>listaTarifasRec= (List<Tarifa>) request.getAttribute("listaTarifas");

			//Formas de Pago
			scFormaPago = new ServicioFormaPago();
			listaFormaPago= scFormaPago.recuperarTodosFormasPago();
			request.setAttribute("listaFormaPago", listaFormaPago);
			//List<FormaPago> listaFormaPagoRec =(List<FormaPago>) request.getAttribute("listaFormaPago");


			//Pintamos el formulario con los datos recibidos
			response.setContentType("text/html");
			PrintWriter out= response.getWriter();

			out.println("<html >");


			out.println("<body>");
			out.println("<form action='Grabar'  method='POST'>");
			out.print("<h3>"+request.getAttribute("mensajeError")+"</h3>");
			out.println(" <div> <label >Codigo Cliente: </label> <input type='text' name='inputCodCliente' value='"+ request.getParameter("inputCodCliente")+"'> </div>");
			out.println("<div> <label >Razon Social: </label><input type='text' name='inputRazonSocial' value='"+ request.getParameter("inputRazonSocial")+"'></div>");
			out.println("<div><label >Telefono: </label><input type='text' name='inputTelefono' value='"+ request.getParameter("inputTelefono")+"'></div>");
			out.println("<div><label >Direccion: </label><input type='text' name='inputDireccion'value='"+ request.getParameter("inputDireccion")+"'></div>");

			if(request.getParameter("inputOferta").equals("S")) {
				out.println("<div><label >Oferta: </label><input type='radio' name='inputOferta' value='S' checked>Si <input type='radio' name='inputOferta' value='N'>No </div>");
			}else {
				out.println("<div><label >Oferta: </label><input type='radio' name='inputOferta' value='S'>Si <input type='radio' name='inputOferta' value='N' checked>No </div>");
			}

			if(request.getParameter("inputAlbFactura").equals("S")) {
				out.println("<div><label >Albaran Factura: </label><input type='radio' name='inputAlbFactura' value='S' checked>Si <input type='radio' name='inputAlbFactura' value='N'>No </div> ");
			}else {
				out.println("<div><label >Albaran Factura: </label><input type='radio' name='inputAlbFactura' value='S'>Si <input type='radio' name='inputAlbFactura' value='N' checked>No </div> ");
			}

			out.println("<div>");
			out.println("<label> Selecciona un IVA: </label>");
			out.println("<select name='inputIva'>");
			if(listaIva!=null){
				for (Iva iva : listaIva) {
					if(request.getParameter("inputIva").equals(iva.getcodIva())) {
						out.println("<option value='"+ iva.getcodIva()+ "' selected> " + iva.gettipoIva() + "</option> "); 
					}else {
						out.println("<option value='"+ iva.getcodIva()+ "'> " + iva.gettipoIva() + "</option>"); 
					}
				}
			}
			out.println("</select>");
			out.println("</div>");

			out.println("</div>");
			out.println("<label>Selecciona una Tarifa: </label>");
			out.println("<select name='inputTarifa'>");
			if( listaTarifas!=null) {
				for (Tarifa tarifa : listaTarifas) {
					if(request.getParameter("inputTarifa").equals(tarifa.getCodTarifa())) {
						out.println("<option value='"+tarifa.getCodTarifa()+"' selected>"+tarifa.getDescripcion()+"</option>");

					}else {
						out.println("<option value='"+tarifa.getCodTarifa()+"' >"+tarifa.getDescripcion()+"</option>");
					}
				}
			}
			out.println("</select>");
			out.println("</div>");

			out.println("<div>");
			out.println("<label>Selecciona una Forma de Pago: </label>");
			out.println("<select name='inputFormaPago'>");
			if(listaFormaPago!=null) {
				for(FormaPago formapago:listaFormaPago) {
					if(request.getParameter("inputFormaPago").equals(formapago.getCodigo())) {
						out.println("<option  value='"+ formapago.getCodigo()+ "' selected>"+formapago.getNumeroVtos()+" pagos, "+ formapago.getDias()+ " dias.");
					}else {
						out.println("<option  value='"+ formapago.getCodigo()+ "' >"+formapago.getNumeroVtos()+" pagos, "+ formapago.getDias()+ " dias.");						
					}
				}
			}
			out.println("</select>");
			out.println("</div>");

			out.println("<button type='submit' >Insertar</button> ");
			out.println("<button type='reset' >Reset</button> ");

			out.println("<button type='button'> <a href='Menu.html'>Menu</a> </button>");//dentro del servlet si hace falta ponerle en enlace completo con .html si es un servlet no

			out.println(" </form>");
			out.println("</body>");
			out.println("</html>");
			out.close();


		}		catch (ServiceException e) {


			e.printStackTrace();// para administrador 
			//Error interno para usuario
			request.setAttribute("mensaje", "error interno");
			salida=("/Fin");
			getServletContext().getRequestDispatcher(salida).forward(request, response);


		}
	}

}
