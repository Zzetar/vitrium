package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConsultarClientes
 */
@WebServlet("/ConsultarClientes")
public class ConsultarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultarClientes() {
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
	private String num;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//como cojo si es borrar o modificar..?¿¿?¿?¿??
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		out.println(request.getParameter("num"));
		
		 num=request.getParameter("num");
			out.println("<html >");
				out.println("<body>");
					out.println("<form action='OperacionesCliente'  method='POST'>");
					out.println("<input type='hidden' name='num' value='"+num+"'>");//pasamos el numero dentro del input tiene que estas oculto
					out.println("<label>Indica un cliente: </label><input type='text' name='inputCliente'>");
					out.println("<button type='submit'>Buscar</button>");
					out.println("</form>");
				out.println("</body>");
			out.println("</html >");
		
	}

}
