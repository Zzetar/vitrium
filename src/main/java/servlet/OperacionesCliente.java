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
import domain.Tarifa;
import exceptions.ServiceException;
import servicios.ServicioCliente;
import servicios.ServicioFormaPago;
import servicios.ServicioIva;
import servicios.ServicioTarifa;

/**
 * Servlet implementation class OperacionesCliente
 */
@WebServlet("/OperacionesCliente")
public class OperacionesCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperacionesCliente() {
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
		
		String num = request.getParameter("num");
		String codigoCliente=request.getParameter("inputCliente");
		String Salida=null;
	//Aqui con el num recibido pintamos un html para VER el cliente, para BORRARLO y otro para MODIFICARLO	
		out.println(num);

		//Si NUM es 1, vemos el cliente y sus datos nada mas
		if(num.equals("1")) {
			Cliente cliente= null;
			ServicioCliente scCliente = new ServicioCliente();
			try {
				cliente=scCliente.recuperarClienteCompletoById(codigoCliente);
				if(cliente!=null) {
					out.println("<html >");
					out.println("<body>");
					out.println("<p>Cliente numero: "+cliente.getCodCli()+"</p>");
					out.println("Razon Social: "+cliente.getRazonSocial()+"</p>");
					out.println("<p>Telefono: "+cliente.getTelf()+"</p>");
					out.println("<p>Direccion: "+cliente.getDireccion()+"</p>");
					out.println("<p>Ofert: "+cliente.getOferta()+"</p>");
					out.println("<p>AlbaranFactura: "+cliente.getAlbFact()+"</p>");
					out.println("<p>IVA: "+cliente.getIva().gettipoIva()+"</p>");
					out.println("<p>Tarifa: "+cliente.getTarifa().getDescripcion()+"</p>");
					out.println("<p>Forma Pago: "+cliente.getFormaPago().getNumeroVtos()+" pago, "+cliente.getFormaPago().getDias()+ " dias.</p>");
					out.println("<button type='button'> <a href='Menu.html'>Volver al Menu</a> </button>");//dentro del servlet si hace falta ponerle en enlace completo con .html
					out.println("</body>");
					out.println("</html >");
				}else {
					Salida=("/Fin");
					request.setAttribute("mensaje","El cliente indicado no existe");
					getServletContext().getRequestDispatcher(Salida).forward(request, response);
				}
			} catch (ServiceException e) {
				e.printStackTrace();// para administrador 
				//Error interno para usuario
				request.setAttribute("mensaje", "error interno");
				Salida=("/Fin");
				getServletContext().getRequestDispatcher(Salida).forward(request, response);
			}
		}
		if(num.equals("2")){

			Cliente cliente= null;
			ServicioCliente scCliente = new ServicioCliente();
			try {
				cliente=scCliente.recuperarClienteCompletoById(codigoCliente);
				if(cliente!=null) {
					out.println("<html >");
					out.println("<body>");
					out.println("<form action='BorrarCliente' method='POST'>");
						out.println("<p>Cliente numero: "+"<input type='text'n readonly='readonly' style='border:0px' name='inputCodCliente'  value='"+cliente.getCodCli()+"'></input></p>");//Con readonly no se puede modificar pero a veces lo lee y con disabled no pasa el valor
						out.println("Razon Social: "+cliente.getRazonSocial()+"</p>");
						out.println("<p>Telefono: "+cliente.getTelf()+"</p>");
						out.println("<p>Direccion: "+cliente.getDireccion()+"</p>");
						out.println("<p>Ofert: "+cliente.getOferta()+"</p>");
						out.println("<p>AlbaranFactura: "+cliente.getAlbFact()+"</p>");
						out.println("<p>IVA: "+cliente.getIva().gettipoIva()+"</p>");
						out.println("<p>Tarifa: "+cliente.getTarifa().getDescripcion()+"</p>");
						out.println("<p>Forma Pago: "+cliente.getFormaPago().getNumeroVtos()+" pago, "+cliente.getFormaPago().getDias()+ " dias.</p>");
						out.println("<button type='submit'> Borrar Cliente </button>");
						out.println("<button type='button'> <a href='Menu.html'>Menu</a> </button>");//dentro del servlet si hace falta ponerle en enlace completo con .html si es un servlet no
					out.println("</form>");
					out.println("</body>");
					out.println("</html >");
				}else {
					Salida=("/Fin");
					request.setAttribute("mensaje","El cliente indicado no existe");
					getServletContext().getRequestDispatcher(Salida).forward(request, response);
				}
			} catch (ServiceException e) {
				e.printStackTrace();// para administrador 
				//Error interno para usuario
				request.setAttribute("mensaje", "error interno");
				Salida=("/Fin");
				getServletContext().getRequestDispatcher(Salida).forward(request, response);
			}
		
		}
		if(num.equals("3")) {
			
			String salida=null;
			ServicioIva scIva=null;
			List<Iva> listaIva= new ArrayList<Iva>();

			ServicioTarifa scTarifas= null;
			List<Tarifa> listaTarifas = new ArrayList<Tarifa>();

			ServicioFormaPago scFormaPago=null;
			List<FormaPago> listaFormaPago= new ArrayList<FormaPago>();
			Cliente cliente=new Cliente();
			ServicioCliente scCliente=new ServicioCliente();
			try {
				cliente=scCliente.recuperarClienteCompletoById(codigoCliente);

				salida="/fin";
				//Iva
				scIva = new ServicioIva();
				listaIva = scIva.recuperarTodosIvas();
				//request.setAttribute("listaIva", listaIva); 
				//List<Iva> listaIvasRec= (List<Iva>) request.getAttribute("listaIva");

				//Tarifa
				scTarifas = new ServicioTarifa();
				listaTarifas = scTarifas.RecuperarTodasTarifas();
				//request.setAttribute("listaTarifas", listaTarifas);
				//List<Tarifa>listaTarifasRec= (List<Tarifa>) request.getAttribute("listaTarifas");

				//Formas de Pago
				scFormaPago = new ServicioFormaPago();
				listaFormaPago= scFormaPago.recuperarTodosFormasPago();
				//request.setAttribute("listaFormaPago", listaFormaPago);
				//List<FormaPago> listaFormaPagoRec =(List<FormaPago>) request.getAttribute("listaFormaPago");		

				
				out.println("<html >");


				out.println("<body>");
				out.println("<form action='ModificarCliente'  method='POST'>");
				out.println(" <div> <label >Codigo Cliente: </label> <input type='text' name='inputCodCliente' readonly='readonly' value='"+ cliente.getCodCli()+"'> </div>");
				out.println("<div> <label >Razon Social: </label><input type='text' name='inputRazonSocial' value='"+ cliente.getRazonSocial()+"'></div>");
				out.println("<div><label >Telefono: </label><input type='text' name='inputTelefono' value='"+ cliente.getTelf()+"'></div>");
				out.println("<div><label >Direccion: </label><input type='text' name='inputDireccion'value='"+ cliente.getDireccion()+"'></div>");

				if(cliente.getOferta().equals("S")) {
					out.println("<div><label >Oferta: </label><input type='radio' name='inputOferta' value='S' checked>Si <input type='radio' name='inputOferta' value='N'>No </div>");
				}else {
					out.println("<div><label >Oferta: </label><input type='radio' name='inputOferta' value='S'>Si <input type='radio' name='inputOferta' value='N' checked>No </div>");
				}

				if(cliente.getAlbFact().equals("S")) {
					out.println("<div><label >Albaran Factura: </label><input type='radio' name='inputAlbFactura' value='S' checked>Si <input type='radio' name='inputAlbFactura' value='N'>No </div> ");
				}else {
					out.println("<div><label >Albaran Factura: </label><input type='radio' name='inputAlbFactura' value='S'>Si <input type='radio' name='inputAlbFactura' value='N' checked>No </div> ");
				}

				out.println("<div>");
				out.println("<label> Selecciona un IVA: </label>");
				out.println("<select name='inputIva'>");
				if(listaIva!=null){
					for (Iva iva : listaIva) {
						if(cliente.getIva().getcodIva().equals(iva.getcodIva())) {
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
						if(cliente.getTarifa().codtarifa.equals(tarifa.getCodTarifa())) {
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
						if(cliente.getFormaPago().codigo.equals(formapago.getCodigo())) {
							out.println("<option  value='"+ formapago.getCodigo()+ "' selected>"+formapago.getNumeroVtos()+" pagos, "+ formapago.getDias()+ " dias.");
						}else {
							out.println("<option  value='"+ formapago.getCodigo()+ "' >"+formapago.getNumeroVtos()+" pagos, "+ formapago.getDias()+ " dias.");						
						}
					}
				}
				out.println("</select>");
				out.println("</div>");

				out.println("<button type='submit' >Modificar</button> ");
				out.println(" </form>");
				out.println("</body>");
				out.println("</html>");
				out.close();


			} catch (Exception e) {


				e.printStackTrace();// para administrador 
				//Error interno para usuario
				request.setAttribute("mensaje", "error interno");
				salida=("/Fin");
				getServletContext().getRequestDispatcher(salida).forward(request, response);


			}


		
		}
	}

}
